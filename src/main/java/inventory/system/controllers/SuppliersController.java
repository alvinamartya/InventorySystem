package inventory.system.controllers;

import inventory.system.model.Supplier;
import inventory.system.service.SupplierService;
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
@RequestMapping("/supplier")
public class SuppliersController {

    @Autowired
    SupplierService supplierService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Supplier> supplierList = supplierService.getAllSupplier();
        model.addAttribute("listSupplier", supplierList);
        return "Supplier/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("supplierObject", new Supplier());
        return "Supplier/Create";
    }

    // save supplier
    @PostMapping("/save")
    public String save(Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return "redirect:/supplier/index";
    }

    // view edit supplier
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplierObject", supplier);

        return "Supplier/Edit";
    }

    // update supplier
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Supplier supplier, BindingResult result) {
        if(result.hasErrors()) {
            supplier.setId(id);
            return "Supplier/Edit";
        }

        supplierService.update(id, supplier);
        return "redirect:/supplier/index";
    }

    // view detail supplier
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplierObject", supplier);

        return "Supplier/Detail";
    }

    // view delete supplier
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplierObject", supplier);

        return "Supplier/Delete";
    }

    // confirm to delete supplier
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id) {
        Supplier supplier = supplierService.getSupplierById(id);
        supplierService.delete(supplier);

        return "redirect:/supplier/index";
    }
}
