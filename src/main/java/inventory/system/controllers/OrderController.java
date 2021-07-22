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
    DriverService driverService;

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductService productService;


    // view index
    @RequestMapping("/index")
    public String index(Model model,  HttpSession httpsession,
                        @SessionAttribute(required=false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            List<Order> ordersList = null;
            if(logged_user.getRole_id()==1){
                ordersList = orderService.getAllOrderByWarehouse(logged_user.getWarehouse_id());
            }
            else if(logged_user.getRole_id()==2){
                ordersList = orderService.getAllOrderByWarehouse(logged_user.getWarehouse_id());
            }


            model.addAttribute("listOrder", ordersList);

            return "Order/Index";
        }
        return "redirect:/login";

    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {

        //----Diambil dari Session
        int level = 3; // 1=Supplier-Pusat, 2=Pusat-Cabang, 3=Cabang-Toko
        //----Diambil dari Session
        String destType = "";
        List<Warehouses> warehouseOriginList = new ArrayList<>();
        List<Warehouses> warehouseDestList = new ArrayList<>();
        List<Product> productList = productService.getAllProduct();
        List<Shelf> shelfList = shelfService.getAllShelfRO();
        List<Driver> driverList = driverService.getAllDriver();

        if (level == 1) {
            warehouseOriginList = warehouseService.getAllWarehousesPusat();
            warehouseDestList = warehouseService.getAllWarehousesPusat();
            destType = "Gudang";
        } else if (level == 2) {
            warehouseOriginList = warehouseService.getAllWarehousesPusat();
            warehouseDestList = warehouseService.getAllWarehousesCabang();

            destType = "Gudang";
        } else if (level == 3) {
            warehouseOriginList = warehouseService.getAllWarehousesCabang();
            warehouseDestList = warehouseService.getAllWarehousesCabang();
            destType = "Toko";
        }

        model.addAttribute("orderObject", new OrderInput(destType));

        model.addAttribute("listDriver", driverList);
        model.addAttribute("listWarehouseOr", warehouseOriginList);
        model.addAttribute("listWarehouseDest", warehouseDestList);
        model.addAttribute("listProduct", productList);
        model.addAttribute("listShelf", shelfList);
        model.addAttribute("level", level);



        return "Order/Create";
    }

    // save order
    @PostMapping("/save")
    public String save(OrderInput orderinput, RedirectAttributes redirectAttrs) {
        orderService.saveOrder(orderinput);
        redirectAttrs.addFlashAttribute("success_create", "Order Successfully Added!");
        return "redirect:/order/index";
    }

    // view detail order
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Order orders = orderService.getOrderById(id);
        model.addAttribute("orderObject", orders);

        List<OrderDetail> listDetail = orderService.getOrderDetail(id);
        model.addAttribute("detailorderObject", listDetail);

        return "Order/Detail";
    }

    //Checked
    @GetMapping("/check/{id}")
    public String check(@PathVariable(value = "id") String id, Model model) {
        Order orders = orderService.getOrderById(id);
        model.addAttribute("orderObject", orders);

        List<OrderDetail> listDetail = orderService.getOrderDetail(id);
        model.addAttribute("detailorderObject", listDetail);

        model.addAttribute("state", "check");
        return "Order/Confirmation";
    }

    @RequestMapping("/check-confirmed/{id}")
    public String checkConfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Gudang";

        orderService.check(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Checked!");
        return "redirect:/order/index";
    }

    //Approved
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable(value = "id") String id, Model model) {
        Order orders = orderService.getOrderById(id);
        model.addAttribute("orderObject", orders);

        List<OrderDetail> listDetail = orderService.getOrderDetail(id);
        model.addAttribute("detailorderObject", listDetail);

        model.addAttribute("state", "approve");
        return "Order/Confirmation";
    }

    @RequestMapping("/approve-confirmed/{id}")
    public String approveConfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Transaksi";

        orderService.approve(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Approved!");
        return "redirect:/order/index";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable(value = "id") String id, Model model) {
        Order orders = orderService.getOrderById(id);
        model.addAttribute("orderObject", orders);

        List<OrderDetail> listDetail = orderService.getOrderDetail(id);
        model.addAttribute("detailorderObject", listDetail);

        model.addAttribute("state", "reject");
        return "Order/Confirmation";
    }

    @RequestMapping("/reject-confirmed/{id}")
    public String rejectConfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Gudang";

        orderService.reject(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Rejected!");
        return "redirect:/order/index";
    }
}
