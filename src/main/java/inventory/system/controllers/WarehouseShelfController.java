package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.model.WarehouseCustomJSONModel;
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
public class WarehouseShelfController {

    @Autowired
    OrderService orderService;

    @Autowired
    WarehousesService warehouseService;

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductService productService;


    // view index
    @RequestMapping("/warehouse-shelf-order")
    public String index(Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Shelf> shelfsList = shelfService.getAllShelfByWarehouseRO(logged_user.getWarehouse_id());
            model.addAttribute("listShelf", shelfsList);
            List<ShelfDetail> listDetail = new ArrayList<>();
            model.addAttribute("detailshelf", listDetail);

            model.addAttribute("shelfObject", new WarehouseCustomJSONModel());

            return "WarehouseShelf/Index";
        }
        return "redirect:/login";

    }

    // view detail order
    @PostMapping("/warehouse-shelf-order")
    public String show(WarehouseCustomJSONModel shelf, Model model, HttpSession httpsession,
                         @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {

            List<Shelf> shelfsList = shelfService.getAllShelfByWarehouseRO(logged_user.getWarehouse_id());
            model.addAttribute("listShelf", shelfsList);
            List<ShelfDetail> listDetail = shelfService.getAllShelfById(shelf.getId());
            model.addAttribute("detailShelf", listDetail);

            model.addAttribute("shelfObject", shelf);

            return "WarehouseShelf/Index";
        }
        return "redirect:/login";
    }

    @RequestMapping("/warehouse-shelf-retur")
    public String indexretur(Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Shelf> shelfsList = shelfService.getAllShelfByWarehouseRR(logged_user.getWarehouse_id());
            model.addAttribute("listShelf", shelfsList);
            List<ShelfDetail> listDetail = new ArrayList<>();
            model.addAttribute("detailshelf", listDetail);

            model.addAttribute("shelfObject", new WarehouseCustomJSONModel());

            return "WarehouseShelf/Index2";
        }
        return "redirect:/login";

    }

    // view detail order
    @PostMapping("/warehouse-shelf-retur")
    public String showretur(WarehouseCustomJSONModel shelf, Model model, HttpSession httpsession,
                       @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {

            List<Shelf> shelfsList = shelfService.getAllShelfByWarehouseRR(logged_user.getWarehouse_id());
            model.addAttribute("listShelf", shelfsList);
            List<ShelfDetail> listDetail = shelfService.getAllShelfById(shelf.getId());
            model.addAttribute("detailShelf", listDetail);

            model.addAttribute("shelfObject", shelf);

            return "WarehouseShelf/Index2";
        }
        return "redirect:/login";
    }

}
