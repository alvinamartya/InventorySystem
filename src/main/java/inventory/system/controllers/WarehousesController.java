package inventory.system.controllers;

import inventory.system.entity.Warehouses;
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
@RequestMapping("/warehouse")
public class WarehousesController {

    @Autowired
    WarehousesService warehousesService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Warehouses> warehousesList = warehousesService.getAllWarehouses();
        model.addAttribute("listWarehouse", warehousesList);
        return "Warehouse/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("warehouseObject", new Warehouses());
        return "Warehouse/Create";
    }

    // save warehouse
    @PostMapping("/save")
    public String save(Warehouses warehouses) {
        warehousesService.saveWarehouses(warehouses);
        return "redirect:/warehouse/index";
    }

    // view edit warehouse
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        Warehouses warehouses = warehousesService.getWarehousesById(id);
        model.addAttribute("warehouseObject", warehouses);

        return "Warehouse/Edit";
    }

    // update warehouse
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Warehouses warehouses,
                         BindingResult result) {
        if (result.hasErrors()) {
            warehouses.setId(id);
            return "Warehouse/Edit";
        }

        warehousesService.update(id, warehouses);
        return "redirect:/warehouse/index";
    }

    // view detail warehouse
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Warehouses warehouses = warehousesService.getWarehousesById(id);
        model.addAttribute("warehouseObject", warehouses);

        return "Warehouse/Detail";
    }

    // view delete warehouse
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model) {
        Warehouses warehouses = warehousesService.getWarehousesById(id);
        model.addAttribute("warehouseObject", warehouses);

        return "Warehouse/Delete";
    }

    // confirm to delete warehouse
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id) {
        Warehouses warehouses = warehousesService.getWarehousesById(id);

        warehousesService.deleteWarehouses(warehouses);
        return "redirect:/warehouse/index";
    }


    // deactivate warehouse
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model){
        Warehouses warehouse = warehousesService.getWarehousesById(id);
        model.addAttribute("warehouseObject", warehouse);

        return "Warehouse/Delete";
    }

    // activated warehouse
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model){
        Warehouses warehouse = warehousesService.getWarehousesById(id);
        model.addAttribute("warehouseObject", warehouse);

        return "Warehouse/Delete";
    }

    // confirm to activate status warehous
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        Warehouses warehouse = warehousesService.getWarehousesById(id);
        warehousesService.activate(warehouse);

        redirectAttrs.addFlashAttribute("success_active", "Warehouse Successfully Activated!");
        return "redirect:/warehouse/index";
    }
}
