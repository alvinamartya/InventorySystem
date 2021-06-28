package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.ShelfService;
import inventory.system.service.WarehousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(Model model) {
        List<Shelf> shelfsList = shelfService.getAllShelf();
        model.addAttribute("listShelf", shelfsList);
        return "Shelf/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("shelfObject", new Shelf());

        List<ProductCategory> categoryList = categoryService.getAllProductCategory();
        model.addAttribute("listCategory", categoryList);

        List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
        model.addAttribute("listWarehouse", warehouseList);

        return "Shelf/Create";
    }

    // save shelf
    @PostMapping("/save")
    public String save(Shelf shelf, RedirectAttributes redirectAttrs) {
        shelfService.saveShelf(shelf);
        redirectAttrs.addFlashAttribute("success_create", "Shelf Successfully Added!");

        return "redirect:/shelf/index";
    }

    // view edit shelf
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        Shelf shelfs = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelfs);

        List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
        model.addAttribute("listWarehouse", warehouseList);

        return "Shelf/Edit";
    }

    // update shelf
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Shelf shelf,
                         BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            shelf.setId(id);
            return "Shelf/Edit";
        }

        shelfService.update(id, shelf);

        redirectAttrs.addFlashAttribute("success_update", "Shelf Successfully Updated!");
        return "redirect:/shelf/index";
    }

    // view detail shelf
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Shelf shelfs = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelfs);

        List<ShelfDetail> listDetail = shelfService.getShelfDetail(id);
        model.addAttribute("detailshelfObject", listDetail);

        return "Shelf/Detail";
    }

    // view delete shelf
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model) {
        Shelf shelfs = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelfs);

        List<ShelfDetail> listDetail = shelfService.getShelfDetail(id);
        model.addAttribute("detailshelfObject", listDetail);

        return "Shelf/Delete";
    }

    // confirm to delete shelf
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        Shelf shelf = shelfService.getShelfById(id);
        shelfService.delete(shelf);

        redirectAttrs.addFlashAttribute("success_delete", "Shelf Successfully Deleted!");
        return "redirect:/shelf/index";
    }

}
