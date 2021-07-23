package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Stores;
import inventory.system.service.StoresService;
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
@RequestMapping("/store")
public class StoresController {

    @Autowired
    StoresService storesService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        List<Stores> storesList = storesService.getAllStores();
        model.addAttribute("listStore", storesList);
        return "Store/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        model.addAttribute("storeObject", new Stores());
        return "Store/Create";
        }
        return "redirect:/login";
    }

    // save store
    @PostMapping("/save")
    public String save(Stores stores, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        storesService.saveStores(stores, logged_user);
        return "redirect:/store/index";
        }
        return "redirect:/login";
    }

    // view edit store
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Edit";
        }
        return "redirect:/login";
    }

    // update store
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Stores store,
                         BindingResult result, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        if (result.hasErrors()) {
            store.setId(id);
            return "Store/Edit";
        }

        storesService.update(id, store, logged_user);
        return "redirect:/store/index";
        }
        return "redirect:/login";
    }

    // view detail store
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Detail";
        }
        return "redirect:/login";
    }

    // view delete store
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete store
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id,
                                  HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);

        storesService.deleteStores(stores, logged_user);
        return "redirect:/store/index";
        }
        return "redirect:/login";
    }

    // deactivate store
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
        }
        return "redirect:/login";
    }

    // activated store
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        model.addAttribute("storeObject", stores);

        return "Store/Delete";
        }
        return "redirect:/login";
    }

    // confirm to activate status store
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Stores stores = storesService.getStoresById(id);
        storesService.activate(stores, logged_user);

        redirectAttrs.addFlashAttribute("success_active", "Store Successfully Activated!");
        return "redirect:/store/index";
        }
        return "redirect:/login";
    }
}
