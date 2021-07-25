package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.*;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    WarehousesService warehouseService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    DriverService driverService;

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductService productService;


    // view index
    @RequestMapping("/index/{level}")
    public String index(@PathVariable(value = "level") int level, Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Order> ordersList = orderService.getAllOrderByWarehouse(logged_user.getWarehouse_id(), level);
            int is_trans_admin = (logged_user.getRole_id() == 1) ? 1 : 0;
            model.addAttribute("is_trans_admin", is_trans_admin);
            model.addAttribute("level", level);
            model.addAttribute("listOrder", ordersList);

            return "Order/Index";
        }
        return "redirect:/login";

    }

    // view create
    @RequestMapping("/create/{level}")
    public String create(@PathVariable(value = "level") int level, Model model, HttpSession httpsession,
                         @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            String destType = "";
            String originType = "";
            String destId = "";
            String originId = "";
            List<Supplier> supplierOriginList = new ArrayList<>();
            List<Warehouses> warehouseOriginList = new ArrayList<>();
            List<Warehouses> warehouseDestList = new ArrayList<>();
            List<Product> productList = productService.getAllProduct();
            List<Shelf> shelfList = shelfService.getAllShelfRO();

            if (level == 1) {
                supplierOriginList = supplierService.getAllSupplier();
                model.addAttribute("listWarehouseOr", supplierOriginList);
                warehouseDestList = warehouseService.getAllWarehousesPusat();
                originType = "Pemasok";
                destType = "Gudang";
                destId = logged_user.getWarehouse_id();
            } else if (level == 2) {
                warehouseOriginList = warehouseService.getAllWarehousesPusat();
                model.addAttribute("listWarehouseOr", warehouseOriginList);
                warehouseDestList = warehouseService.getAllWarehousesCabang();
                originType = "Gudang";
                destType = "Gudang";
                originId = logged_user.getWarehouse_id();
            } else if (level == 3) {
                warehouseOriginList = warehouseService.getAllWarehousesCabang();
                model.addAttribute("listWarehouseOr", warehouseOriginList);
                warehouseDestList = warehouseService.getAllWarehousesCabang();
                originType = "Gudang";
                destType = "Toko";
                originId = logged_user.getWarehouse_id();
            }

            model.addAttribute("orderObject", new OrderInput(originType, destType, originId, destId));
            model.addAttribute("listWarehouseDest", warehouseDestList);
            model.addAttribute("listProduct", productList);
            model.addAttribute("listShelf", shelfList);
            model.addAttribute("level", level);

            return "Order/Create";
        }
        return "redirect:/login";
    }

    // save order
    @PostMapping("/save/{level}")
    public String save(@PathVariable(value = "level") int level, OrderInput orderinput, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            orderService.saveOrder(orderinput, logged_user);
            redirectAttrs.addFlashAttribute("success_create", "Order Successfully Added!");
            return "redirect:/order/index/" + level;
        }
        return "redirect:/login";
    }

    // view detail order
    @GetMapping("/detail/{id}/{level}")
    public String detail(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level, Model model, HttpSession httpsession,
                         @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Order orders = orderService.getOrderById(id);
            model.addAttribute("orderObject", orders);

            List<OrderDetail> listDetail = orderService.getOrderDetail(id);
            model.addAttribute("detailorderObject", listDetail);
            model.addAttribute("level", level);
            if (!orders.getOrigin_type().equals("Pemasok")) {
                Driver driver = driverService.getDriverById(orders.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }
            return "Order/Detail";
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
            Order orders = orderService.getOrderById(id);
            model.addAttribute("orderObject", orders);

            List<OrderDetail> listDetail = orderService.getOrderDetail(id);
            model.addAttribute("detailorderObject", listDetail);
            model.addAttribute("level", level);

            model.addAttribute("state", "check");

            if (!orders.getOrigin_type().equals("Pemasok")) {
                Driver driver = driverService.getDriverById(orders.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }

            return "Order/Confirmation";
        }
        return "redirect:/login";
    }

    @RequestMapping("/check-confirmed/{id}/{level}")
    public String checkConfirmed(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level,
                                 RedirectAttributes redirectAttrs, HttpSession httpsession,
                                 @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            String staffName = logged_user.getName();

            orderService.check(id, staffName);

            redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Checked!");
            return "redirect:/order/index/" + level;
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
            Order orders = orderService.getOrderById(id);
            model.addAttribute("orderObject", orders);

            List<OrderDetail> listDetail = orderService.getOrderDetail(id);
            model.addAttribute("detailorderObject", listDetail);
            model.addAttribute("level", level);


            model.addAttribute("state", "approve");

            if (!orders.getOrigin_type().equals("Pemasok")) {
                Driver driver = driverService.getDriverById(orders.getDriver_id());
                model.addAttribute("drivername", driver.getName());
            }

            return "Order/Confirmation";
        }
        return "redirect:/login";
    }

    @RequestMapping("/approve-confirmed/{id}/{level}")
    public String approveConfirmed(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level,
                                   RedirectAttributes redirectAttrs, HttpSession httpsession,
                                   @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            String staffName = logged_user.getName();

            orderService.approve(id, staffName);

            redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Approved!");
            return "redirect:/order/index/" + level;
        }
        return "redirect:/login";
//        List<OrderDetail> orderDetails = orderService.getOrderDetail(id);
//        orderService.moveShelfDetail(orderDetails);

//        return "redirect:/order/index/" + level;
    }

    @RequestMapping("/reject-confirmed/{id}/{level}")
    public String rejectConfirmed(@PathVariable(value = "id") String id, @PathVariable(value = "level") String level,
                                  RedirectAttributes redirectAttrs, HttpSession httpsession,
                                  @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            String staffName = logged_user.getName();

            orderService.reject(id, staffName);

            redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Rejected!");
            return "redirect:/order/index/" + level;
        }
        return "redirect:/login";
    }
}
