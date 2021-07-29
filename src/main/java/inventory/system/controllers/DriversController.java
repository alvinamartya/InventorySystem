package inventory.system.controllers;

import inventory.system.entity.Driver;
import inventory.system.entity.LoggedUser;
import inventory.system.entity.Warehouses;
import inventory.system.service.DriverService;
import inventory.system.service.WarehousesService;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriversController {

    @Resource
    DriverService driverService;

    @Resource
    WarehousesService warehouseService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Driver> driversList = driverService.getAllDriver();
            model.addAttribute("listDriver", driversList);
            return "Driver/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("driverObject", new Driver());

            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);

            return "Driver/Create";
        }
        return "redirect:/login";
    }

    // save driver
    @PostMapping("/save")
    public String save(Driver driver, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            driverService.saveDriver(driver, logged_user);
            redirectAttrs.addFlashAttribute("success_create", "Driver Successfully Added!");
            return "redirect:/driver/index";
        }
        return "redirect:/login";
    }

    // view edit driver
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver drivers = driverService.getDriverById(id);
            model.addAttribute("driverObject", drivers);

            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);

            return "Driver/Edit";
        }
        return "redirect:/login";
    }

    // update driver
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Driver driver,
                         BindingResult result, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            if (result.hasErrors()) {
                driver.setId(id);
                return "Driver/Edit";
            }

            driverService.update(id, driver, logged_user);

            redirectAttrs.addFlashAttribute("success_update", "Driver Successfully Updated!");
            return "redirect:/driver/index";
        }
        return "redirect:/login";
    }

    // view detail driver
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver drivers = driverService.getDriverById(id);
            model.addAttribute("driverObject", drivers);

            return "Driver/Detail";
        }
        return "redirect:/login";
    }

    // view delete driver
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver drivers = driverService.getDriverById(id);
            model.addAttribute("driverObject", drivers);

            return "Driver/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete driver
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver driver = driverService.getDriverById(id);
            driverService.delete(driver, logged_user);

            redirectAttrs.addFlashAttribute("success_deactive", "Driver Successfully Deactivated!");
            return "redirect:/driver/index";
        }
        return "redirect:/login";
    }

    // deactivate driver
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver drivers = driverService.getDriverById(id);
            model.addAttribute("driverObject", drivers);

            return "Driver/Delete";
        }
        return "redirect:/login";
    }

    // activated driver
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") Integer id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver drivers = driverService.getDriverById(id);
            model.addAttribute("driverObject", drivers);

            return "Driver/Delete";
        }
        return "redirect:/login";
    }

    // confirm to activate status driver
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Driver driver = driverService.getDriverById(id);
            driverService.activate(driver, logged_user);

            redirectAttrs.addFlashAttribute("success_active", "Driver Successfully Activated!");
            return "redirect:/driver/index";
        }
        return "redirect:/login";
    }
}
