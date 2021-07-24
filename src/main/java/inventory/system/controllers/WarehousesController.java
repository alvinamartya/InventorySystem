package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Warehouses;
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
@RequestMapping("/warehouse")
public class WarehousesController {

    @Autowired
    WarehousesService warehousesService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            List<Warehouses> warehousesList = warehousesService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehousesList);
            return "Warehouse/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("warehouseObject", new Warehouses());
            return "Warehouse/Create";
        }
        return "redirect:/login";
    }

    // save warehouse
    @PostMapping("/save")
    public String save(Warehouses warehouses, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            warehousesService.saveWarehouses(warehouses, logged_user);
            return "redirect:/warehouse/index";
        }
        return "redirect:/login";
    }

    // view edit warehouse
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouses = warehousesService.getWarehousesById(id);
            model.addAttribute("warehouseObject", warehouses);

            return "Warehouse/Edit";
        }
        return "redirect:/login";
    }

    // update warehouse
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Warehouses warehouses,
                         BindingResult result, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            if (result.hasErrors()) {
                warehouses.setId(id);
                return "Warehouse/Edit";
            }

            warehousesService.update(id, warehouses, logged_user);
            return "redirect:/warehouse/index";
        }
        return "redirect:/login";
    }

    // view detail warehouse
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouses = warehousesService.getWarehousesById(id);
            model.addAttribute("warehouseObject", warehouses);

            return "Warehouse/Detail";
        }
        return "redirect:/login";
    }

    // view delete warehouse
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouses = warehousesService.getWarehousesById(id);
            model.addAttribute("warehouseObject", warehouses);

            return "Warehouse/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete warehouse
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouses = warehousesService.getWarehousesById(id);

            warehousesService.deleteWarehouses(warehouses, logged_user);
            return "redirect:/warehouse/index";
        }
        return "redirect:/login";
    }


    // deactivate warehouse
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouse = warehousesService.getWarehousesById(id);
            model.addAttribute("warehouseObject", warehouse);

            return "Warehouse/Delete";
        }
        return "redirect:/login";
    }

    // activated warehouse
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouse = warehousesService.getWarehousesById(id);
            model.addAttribute("warehouseObject", warehouse);

            return "Warehouse/Delete";
        }
        return "redirect:/login";
    }

    // confirm to activate status warehouse
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            Warehouses warehouse = warehousesService.getWarehousesById(id);
            warehousesService.activate(warehouse, logged_user);

            redirectAttrs.addFlashAttribute("success_active", "Warehouse Successfully Activated!");
            return "redirect:/warehouse/index";
        }
        return "redirect:/login";
    }
}
