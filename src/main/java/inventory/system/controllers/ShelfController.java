package inventory.system.controllers;

import inventory.system.entity.Shelf;
import inventory.system.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shelf")
public class ShelfController {

    @Autowired
    ShelfService shelfService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Shelf> shelfList = shelfService.getAllShelf();
        model.addAttribute("listShelf", shelfList);
        return "Shelf/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("shelfObject", new Shelf());
        return "Shelf/Create";
    }

    // save shelf
    @PostMapping("/save")
    public String save(Shelf shelf) {
        shelfService.saveShelf(shelf);
        return "redirect:/shelf/index";
    }

    // view edit shelf
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        Shelf shelf = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelf);

        return "Shelf/Edit";
    }

    // update shelf
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Shelf shelf, BindingResult result) {
        if(result.hasErrors()) {
            shelf.setId(id);
            return "Shelf/Edit";
        }

        shelfService.update(id, shelf);
        return "redirect:/shelf/index";
    }

    // view detail shelf
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Shelf shelf = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelf);

        return "Shelf/Detail";
    }

    // view delete shelf
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model) {
        Shelf shelf = shelfService.getShelfById(id);
        model.addAttribute("shelfObject", shelf);

        return "Shelf/Delete";
    }

    // confirm to delete shelf
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id) {
        Shelf shelf = shelfService.getShelfById(id);
        shelfService.delete(shelf);

        return "redirect:/shelf/index";
    }
}
