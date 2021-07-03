package inventory.system.controllers;

import inventory.system.entity.Driver;
import inventory.system.entity.LoggedUser;
import inventory.system.entity.Warehouses;
import inventory.system.service.DriverService;
import inventory.system.service.WarehousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriversController {

    @Autowired
    DriverService driverService;

    @Autowired
    WarehousesService warehouseService;

    public boolean isLogin(LoggedUser logged_user, HttpSession httpsession){
        if(httpsession.getAttribute("logged_user")==null || logged_user.getId()==null){
            return false;
        }
        return true;
    }
    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user) {
        if(isLogin(logged_user,httpsession)){
            List<Driver> driversList = driverService.getAllDriver();
            model.addAttribute("listDriver", driversList);
            return "Driver/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("driverObject", new Driver());

        List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
        model.addAttribute("listWarehouse", warehouseList);

        return "Driver/Create";
    }

    // save driver
    @PostMapping("/save")
    public String save(Driver driver, RedirectAttributes redirectAttrs, @SessionAttribute("logged_user") LoggedUser loggedUser) {

        driverService.saveDriver(driver, loggedUser);
        redirectAttrs.addFlashAttribute("success_create", "Driver Successfully Added!");
        return "redirect:/driver/index";
    }

    // view edit driver
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model) {
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
        model.addAttribute("listWarehouse", warehouseList);

        return "Driver/Edit";
    }

    // update driver
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Driver driver,
                         BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            driver.setId(id);
            return "Driver/Edit";
        }

        driverService.update(id, driver);

        redirectAttrs.addFlashAttribute("success_update", "Driver Successfully Updated!");
        return "redirect:/driver/index";
    }

    // view detail driver
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model) {
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        return "Driver/Detail";
    }

    // view delete driver
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model) {
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        return "Driver/Delete";
    }

    // confirm to delete driver
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Driver driver = driverService.getDriverById(id);
        driverService.delete(driver);

        redirectAttrs.addFlashAttribute("success_deactive", "Driver Successfully Deactivated!");
        return "redirect:/driver/index";
    }

    // deactivate driver
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") Integer id, Model model){
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        return "Driver/Delete";
    }


    // activated driver
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") Integer id, Model model){
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        return "Driver/Delete";
    }

    // confirm to activate status driver
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Driver driver = driverService.getDriverById(id);
        driverService.activate(driver);

        redirectAttrs.addFlashAttribute("success_active", "Driver Successfully Activated!");
        return "redirect:/driver/index";
    }
}
