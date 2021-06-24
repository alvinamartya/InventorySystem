package inventory.system.controllers;

import inventory.system.entity.Stores;
import inventory.system.service.StoresService;
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
@RequestMapping("/store")
public class StoresController {

    @Autowired
    StoresService storesService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Stores> storesList = storesService.getAllStores();
        model.addAttribute("listStore", storesList);
        return "Store/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("storeObject", new Stores());
        return "Store/Create";
    }

    // save store
    @PostMapping("/save")
    public String save(Stores stores) {
        storesService.saveStores(stores);
        return "redirect:/store/index";
    }

    // view edit store
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model) {
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Edit";
    }

    // update store
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Stores store,
                         BindingResult result) {
        if (result.hasErrors()) {
            store.setId(id);
            return "Store/Edit";
        }

        storesService.update(id, store);
        return "redirect:/store/index";
    }

    // view detail store
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model) {
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Detail";
    }

    // view delete store
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model) {
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
    }

    // confirm to delete store
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id) {
        Stores stores = storesService.getStoresById(id);

        storesService.deleteStores(stores);
        return "redirect:/store/index";
    }

    // deactivate store
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") Integer id, Model model){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
    }

    // activated store
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") Integer id, Model model){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
    }

    // confirm to activate status store
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Stores stores = storesService.getStoresById(id);
        storesService.activate(stores);

        redirectAttrs.addFlashAttribute("success_active", "Store Successfully Activated!");
        return "redirect:/store/index";
    }
}
