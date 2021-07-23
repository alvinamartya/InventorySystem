package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.*;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/retur")
public class ReturController {

    @Autowired
    ReturService returService;

    @Autowired
    WarehousesService warehousesService;

    @Autowired
    StoresService storesService;

    @Autowired
    DriverService driverService;

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductService productService;

    //view Index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Retur> returList = returService.getAllRetur();
        model.addAttribute("listRetur", returList);
        return "Retur/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("returObject", new ReturInput());

        //----Diambil dari Session
        int level = 2; // 1=Supplier-Pusat, 2=Pusat-Cabang, 3=Cabang-Toko
        //----Diambil dari Session

        if(level == 1){
            List<Stores> storeListRetur = storesService.getAllStores();
            model.addAttribute("listStoreRetur", storeListRetur);

            List<Warehouses> warehouseListDest = warehousesService.getAllWarehousesCabang();
            model.addAttribute("listWarehouseDest", warehouseListDest);

            List<Product> productList = productService.getAllProduct();
            model.addAttribute("listProduct", productList);

            List<Shelf> shelfList = shelfService.getAllShelfRR();
            model.addAttribute("listShelf", shelfList);

            model.addAttribute("level", 1);
        }
        else if(level == 2){
            List<Stores> storeListRetur = storesService.getAllStores();
            model.addAttribute("listStoreRetur", storeListRetur);

            List<Warehouses> warehouseListDest = warehousesService.getAllWarehousesCabang();
            model.addAttribute("listWarehouseDest", warehouseListDest);

            List<Driver> driverList = driverService.getAllDriver();
            model.addAttribute("listDriver", driverList);

            List<Product> productList = productService.getAllProduct();
            model.addAttribute("listProduct", productList);

            List<Shelf> shelfList = shelfService.getAllShelfRO();
            model.addAttribute("listShelf", shelfList);

            model.addAttribute("level", 2);
        }
        else if(level == 3){
            List<Stores> storeListRetur = storesService.getAllStores();
            model.addAttribute("listStoreRetur", storeListRetur);

            List<Warehouses> warehouseListDest = warehousesService.getAllWarehousesCabang();
            model.addAttribute("listWarehouseDest", warehouseListDest);

            List<Driver> driverList = driverService.getAllDriver();
            model.addAttribute("listDriver", driverList);

            List<Product> productList = productService.getAllProduct();
            model.addAttribute("listProduct", productList);

            List<Shelf> shelfList = shelfService.getAllShelfRO();
            model.addAttribute("listShelf", shelfList);

            model.addAttribute("level", 3);
        }

        return "Retur/Create";
    }

    // save retur
    @PostMapping("/save")
    public String save(ReturInput returInput, RedirectAttributes redirectAttrs) {
        returService.saveRetur(returInput);
        redirectAttrs.addFlashAttribute("success_create", "Retur Successfully Added!");
        return "redirect:/retur/index";
    }

    // view detail retur
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Retur retur = returService.getReturById(id);
        model.addAttribute("returObject", retur);

        List<ReturDetail> listDetail = returService.getReturDetail(id);
        model.addAttribute("detailReturObject", listDetail);

        return "Retur/Detail";
    }

    //Checked
    @GetMapping("/check/{id}")
    public String check(@PathVariable(value = "id") String id, Model model) {
        Retur retur = returService.getReturById(id);
        model.addAttribute("returObject", retur);

        List<ReturDetail> listDetail = returService.getReturDetail(id);
        model.addAttribute("detailReturObject", listDetail);

        model.addAttribute("state", "check");
        return "Retur/Confirmation";
    }

    @RequestMapping("/check-confirmed/{id}")
    public String checkconfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Gudang";

        returService.check(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Retur Successfully Checked!");
        return "redirect:/retur/index";
    }

    //Approved
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable(value = "id") String id, Model model) {
        Retur retur = returService.getReturById(id);
        model.addAttribute("returObject", retur);

        List<ReturDetail> listDetail = returService.getReturDetail(id);
        model.addAttribute("detailReturObject", listDetail);

        model.addAttribute("state", "approve");
        return "Retur/Confirmation";
    }

    @RequestMapping("/approve-confirmed/{id}")
    public String approveconfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Transaksi";

        returService.approve(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Retur Successfully Approved!");
        return "redirect:/retur/index";
    }
}
