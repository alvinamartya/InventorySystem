package inventory.system.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import inventory.system.entity.*;
import inventory.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public String index(Model model) {
        List<Order> ordersList = orderService.getAllOrder();
        model.addAttribute("listOrder", ordersList);
        return "Order/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("orderObject", new OrderInput());


        //----Diambil dari Session
        int level = 2; // 1=Supplier-Pusat, 2=Pusat-Cabang, 3=Cabang-Toko
        //----Diambil dari Session



        if(level == 1){
            List<Warehouses> warehouseListOr = warehouseService.getAllWarehousesPusat();
            model.addAttribute("listWarehouseOr", warehouseListOr);

            List<Warehouses> warehouseListDest = warehouseService.getAllWarehousesPusat();
            model.addAttribute("listWarehouseDest", warehouseListDest);

            List<Product> productList = productService.getAllProduct();
            model.addAttribute("listProduct", productList);

            List<Shelf> shelfList = shelfService.getAllShelfRO();
            model.addAttribute("listShelf", shelfList);

            model.addAttribute("level", 1);
        }
        else if(level == 2){
            List<Warehouses> warehouseListOr = warehouseService.getAllWarehousesPusat();
            model.addAttribute("listWarehouseOr", warehouseListOr);

            List<Warehouses> warehouseListDest = warehouseService.getAllWarehousesCabang();
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
            List<Warehouses> warehouseListOr = warehouseService.getAllWarehousesCabang();
            model.addAttribute("listWarehouseOr", warehouseListOr);

            List<Warehouses> warehouseListDest = warehouseService.getAllWarehousesCabang();
            model.addAttribute("listWarehouseDest", warehouseListDest);

            List<Driver> driverList = driverService.getAllDriver();
            model.addAttribute("listDriver", driverList);

            List<Product> productList = productService.getAllProduct();
            model.addAttribute("listProduct", productList);

            List<Shelf> shelfList = shelfService.getAllShelfRO();
            model.addAttribute("listShelf", shelfList);

            model.addAttribute("level", 3);
        }


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
    public String checkconfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
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
    public String approveconfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
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
    public String rejectconfirmed(@PathVariable(value = "id") String id, RedirectAttributes redirectAttrs) {
        //**----Dari Session
        String staffName = "Admin Gudang";

        orderService.reject(id, staffName);

        redirectAttrs.addFlashAttribute("success_checked", "Order Successfully Rejected!");
        return "redirect:/order/index";
    }


}
