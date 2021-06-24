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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String save(Supplier supplier, RedirectAttributes redirectAttrs) {
        supplierService.saveSupplier(supplier);
        redirectAttrs.addFlashAttribute("success_create", "Supplier Successfully Added!");
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
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        Supplier supplier = supplierService.getSupplierById(id);
        supplierService.delete(supplier);

        redirectAttrs.addFlashAttribute("success_deactive", "Supplier Successfully Deactivated!");
        return "redirect:/supplier/index";
    }

    // deactivate supplier
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplierObject", supplier);

        return "Supplier/Delete";
    }

    // activated supplier
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplierObject", supplier);

        return "Supplier/Delete";
    }

    // activate status supplier
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        Supplier supplier = supplierService.getSupplierById(id);
        supplierService.activate(supplier);

        redirectAttrs.addFlashAttribute("success_active", "Supplier Successfully Activated!");
        return "redirect:/supplier/index";
    }
}
