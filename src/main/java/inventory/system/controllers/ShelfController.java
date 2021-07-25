package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.ShelfService;
import inventory.system.service.WarehousesService;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shelf")
public class ShelfController {

    @Autowired
    ShelfService shelfService;

    @Autowired
    ProductCategoryService categoryService;

    @Autowired
    WarehousesService warehouseService;

    // view index
    @RequestMapping("/index")
    public String index(Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Shelf> shelfsList = shelfService.getAllShelf();
            model.addAttribute("listShelf", shelfsList);
            return "Shelf/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("shelfObject", new Shelf());

            List<ProductCategory> categoryList = categoryService.getAllProductCategory();
            model.addAttribute("listCategory", categoryList);

            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);

            return "Shelf/Create";
        }
        return "redirect:/login";
    }

    // save shelf
    @PostMapping("/save")
    public String save(Shelf shelf, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            shelfService.saveShelf(shelf, logged_user);
            redirectAttrs.addFlashAttribute("success_create", "Shelf Successfully Added!");

            return "redirect:/shelf/index";
        }
        return "redirect:/login";
    }

    // view edit shelf
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Shelf shelfs = shelfService.getShelfById(id);
            model.addAttribute("shelfObject", shelfs);

            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);

            return "Shelf/Edit";
        }
        return "redirect:/login";
    }

    // update shelf
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Shelf shelf,
                         BindingResult result, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            if (result.hasErrors()) {
                shelf.setId(id);
                return "Shelf/Edit";
            }

            shelfService.update(id, logged_user);
            redirectAttrs.addFlashAttribute("success_update", "Shelf Successfully Updated!");
            return "redirect:/shelf/index";
        }
        return "redirect:/login";
    }

    // view detail shelf
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Shelf shelfs = shelfService.getShelfById(id);
            model.addAttribute("shelfObject", shelfs);

            List<ShelfDetail> listDetail = shelfService.getShelfDetail(id);
            model.addAttribute("detailshelfObject", listDetail);

            return "Shelf/Detail";
        }
        return "redirect:/login";
    }

    // view delete shelf
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Shelf shelfs = shelfService.getShelfById(id);
            model.addAttribute("shelfObject", shelfs);

            List<ShelfDetail> listDetail = shelfService.getShelfDetail(id);
            model.addAttribute("detailshelfObject", listDetail);

            return "Shelf/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete shelf
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Shelf shelf = shelfService.getShelfById(id);
            shelfService.delete(shelf);

            redirectAttrs.addFlashAttribute("success_delete", "Shelf Successfully Deleted!");
            return "redirect:/shelf/index";
        }
        return "redirect:/login";
    }
}
