package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Supplier;
import inventory.system.entity.SupplierDetail;
import inventory.system.service.SupplierService;
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
@RequestMapping("/supplier")
public class SuppliersController {

    @Autowired
    SupplierService supplierService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<Supplier> supplierList = supplierService.getAllSupplier();
            model.addAttribute("listSupplier", supplierList);
            return "Supplier/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("supplierObject", new Supplier());
            return "Supplier/Create";
        }
        return "redirect:/login";
    }

    // save supplier
    @PostMapping("/save")
    public String save(Supplier supplier, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            supplierService.saveSupplier(supplier);
            redirectAttrs.addFlashAttribute("success_create", "Supplier Successfully Added!");
            return "redirect:/supplier/index";
        }
        return "redirect:/login";
    }

    // view edit supplier
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplierObject", supplier);

            return "Supplier/Edit";
        }
        return "redirect:/login";
    }

    // update supplier
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Supplier supplier,
                         RedirectAttributes redirectAttrs, BindingResult result,
                         HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            if (result.hasErrors()) {
                supplier.setId(id);
                return "Supplier/Edit";
            }

            supplierService.update(id, supplier);
            redirectAttrs.addFlashAttribute("success_update", "Supplier Successfully Updated!");
            return "redirect:/supplier/index";
        }
        return "redirect:/login";
    }

    // view detail supplier
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplierObject", supplier);

            return "Supplier/Detail";
        }
        return "redirect:/login";
    }

    // view delete supplier
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model,HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplierObject", supplier);

            return "Supplier/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete supplier
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            supplierService.delete(supplier);

            redirectAttrs.addFlashAttribute("success_deactive", "Supplier Successfully Deactivated!");
            return "redirect:/supplier/index";
        }
        return "redirect:/login";
    }

    // deactivate supplier
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplierObject", supplier);

            return "Supplier/Delete";
        }
        return "redirect:/login";
    }

    // activated supplier
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplierObject", supplier);

            return "Supplier/Delete";
        }
        return "redirect:/login";
    }

    // activate status supplier
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Supplier supplier = supplierService.getSupplierById(id);
            supplierService.activate(supplier);

            redirectAttrs.addFlashAttribute("success_active", "Supplier Successfully Activated!");
            return "redirect:/supplier/index";
        }
        return "redirect:/login";
    }
}
