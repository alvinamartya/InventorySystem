package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.*;
import inventory.system.utils.Session;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/retur")
public class ReturController {

    @Autowired
    ReturService returService;

    @Autowired
    WarehousesService warehousesService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    StoresService storesService;

    @Autowired
    DriverService driverService;

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductService productService;

    //view Index
    @RequestMapping("/index/{level}")
    public String index(@PathVariable(value = "level") int level, Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Retur> returList = returService.getAllReturByWarehouse(logged_user.getWarehouse_id(), level);
            int is_trans_admin = (logged_user.getRole_id() == 1) ? 1 : 0;
            model.addAttribute("is_trans_admin", is_trans_admin);
            model.addAttribute("level", level);
            model.addAttribute("listRetur", returList);

            return "Retur/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create/{level}")
    public String create(@PathVariable(value = "level") int level, Model model, HttpSession httpsession,
                         @SessionAttribute(required = false) LoggedUser logged_user) {
        model.addAttribute("returObject", new ReturInput());

        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            String destType = "";
            String originType = "";
            String destId = "";
            String originId = "";
            List<Supplier> supplierDestList;
            List<Warehouses> warehouseOriginList;
            List<Stores> storeOriginList;
            List<Driver> driverList = new ArrayList<>();
            List<Warehouses> warehouseDestList;
            List<Product> productList = productService.getAllProduct();
            List<Shelf> shelfList = shelfService.getAllShelfRR();

            if (level == 3) {
                warehouseOriginList = warehousesService.getWarehousesPusatById(logged_user.getWarehouse_id());
                model.addAttribute("listOriginRe", warehouseOriginList);
                supplierDestList = supplierService.getAllSupplier();
                model.addAttribute("listWarehouseRe", supplierDestList);
                driverList = warehousesService.getDriverByWarehouse(logged_user.getWarehouse_id());
                originType = "W";
                destType = "S";
                destId = logged_user.getWarehouse_id();
            } else if (level == 2) {
                warehouseOriginList = warehousesService.getAllWarehousesCabang();
                model.addAttribute("listOriginRe", warehouseOriginList);
                warehouseDestList = warehousesService.getAllWarehousesPusat();
                model.addAttribute("listWarehouseRe", warehouseDestList);
                originType = "W";
                destType = "W";
                originId = logged_user.getWarehouse_id();
            } else if (level == 1) {
                storeOriginList = warehousesService.getStoreByCabang(logged_user.getWarehouse_id());
                model.addAttribute("listOriginRe", storeOriginList);
                warehouseDestList = warehousesService.getCabangByStore(logged_user.getWarehouse_id());
                model.addAttribute("listWarehouseRe", warehouseDestList);
                originType = "T";
                destType = "W";
                originId = logged_user.getWarehouse_id();
            }

            model.addAttribute("returObject", new ReturInput(originType, destType, originId, destId));

            model.addAttribute("listDriver", driverList);
            model.addAttribute("listProduct", productList);
            model.addAttribute("listShelf", shelfList);
            model.addAttribute("level", level);

            return "Retur/Create";
        }
        return "redirect:/login";
    }

    // save retur
    @PostMapping("/save/{level}")
    public String save(@PathVariable(value = "level") int level, ReturInput returInput, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            returService.saveRetur(returInput, logged_user);
            redirectAttrs.addFlashAttribute("success_create", "Retur Successfully Added!");

            return "redirect:/retur/index/" + level;

        }
        return "redirect:/login";
    }

    // view detail retur
    @GetMapping("/detail/{id}/{level}")
    public String detail(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level, Model model, HttpSession httpsession,
                         @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else  if (Session.isLogin(logged_user, httpsession)) {
            Retur retur = returService.getReturById(id);
            model.addAttribute("returObject", retur);

            List<ReturDetail> listDetail = returService.getReturDetail(id);
            model.addAttribute("detailReturObject", listDetail);
            model.addAttribute("level", level);
            if (!retur.getOrder_type().equals("T")) {
                Driver driver = driverService.getDriverById(retur.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }
            return "Retur/Detail";
        }
        return "redirect:/login";
    }

    //Checked
    @GetMapping("/check/{id}/{level}")
    public String check(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level, Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Retur retur = returService.getReturById(id);
            model.addAttribute("returObject", retur);

            List<ReturDetail> listDetail = returService.getReturDetail(id);
            model.addAttribute("detailReturObject", listDetail);
            model.addAttribute("level", level);

            model.addAttribute("state", "check");

            if (!retur.getOrder_type().equals("T")) {
                Driver driver = driverService.getDriverById(retur.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }

            return "Retur/Confirmation";
        }
        return "redirect:/login";
    }

    @RequestMapping("/check-confirmed/{id}/{level}")
    public String checkconfirmed(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level,
                                 RedirectAttributes redirectAttrs, HttpSession httpsession,
                                 @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            //**----Dari Session
            String staffName = logged_user.getName();

            returService.check(id, staffName);

            redirectAttrs.addFlashAttribute("success_checked", "Retur Successfully Checked!");
            return "redirect:/retur/index/" + level;
        }
        return "redirect:/login";
    }

    //Approved
    @GetMapping("/approve/{id}/{level}")
    public String approve(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level, Model model, HttpSession httpsession,
                          @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Retur retur = returService.getReturById(id);
            model.addAttribute("returObject", retur);

            List<ReturDetail> listDetail = returService.getReturDetail(id);
            model.addAttribute("detailReturObject", listDetail);
            model.addAttribute("level", level);
            model.addAttribute("state", "approve");

            if (!retur.getOrder_type().equals("T")) {
                Driver driver = driverService.getDriverById(retur.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }

            return "Retur/Confirmation";
        }
        return "redirect:/login";
    }

    @RequestMapping("/approve-confirmed/{id}/{level}")
    public String approveconfirmed(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level,
                                   RedirectAttributes redirectAttrs, HttpSession httpsession,
                                   @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {

            boolean error = returService.moveShelfDetailRetur(id);
            if(!error) {
                String staffName = logged_user.getName();
                returService.approve(id, staffName);
                redirectAttrs.addFlashAttribute("success_checked", "Retur Successfully Approved!");
            } else {
                redirectAttrs.addFlashAttribute("success_checked", "Retur failed to be approved");
            }
            return "redirect:/retur/index/" + level;
        }
        return "redirect:/login";
    }
}