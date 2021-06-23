package inventory.system.controllers;

import inventory.system.model.Driver;
import inventory.system.service.DriverService;
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
@RequestMapping("/driver")
public class DriversController {

    @Autowired
    DriverService driverService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Driver> driversList = driverService.getAllDriver();
        model.addAttribute("listDriver", driversList);
        return "Driver/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("driverObject", new Driver());
        return "Driver/Create";
    }

    // save driver
    @PostMapping("/save")
    public String save(Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/driver/index";
    }

    // view edit driver
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model) {
        Driver drivers = driverService.getDriverById(id);
        model.addAttribute("driverObject", drivers);

        return "Driver/Edit";
    }

    // update driver
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Driver driver,
                         BindingResult result) {
        if (result.hasErrors()) {
            driver.setId(id);
            return "Driver/Edit";
        }

        driverService.update(id, driver);
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
    public String deleteConfirmed(@PathVariable("id") int id) {
        Driver driver = driverService.getDriverById(id);

        driverService.delete(driver);
        return "redirect:/driver/index";
    }
}
