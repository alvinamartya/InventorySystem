package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.SupplierDetailService;
import inventory.system.service.SupplierService;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/supplierDetail")
public class SupplierDetailsController {

    @Autowired
    SupplierDetailService supplierDetailService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    SupplierService supplierService;

    // view index
    @GetMapping("/index/{id}")
    public String index(Model model, @PathVariable(value = "id") String supplier_id, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("supplierDetailObject", supplier_id);

            List<SupplierDetail> supplierDetailList = supplierDetailService.getSupplierDetailByIdSup(supplier_id);
            model.addAttribute("listSupDetail", supplierDetailList);

            return "SupplierDetail/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable(value = "id") String supplier_id, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("supplierDetailObject", new SupplierDetail());

            Supplier supplier = supplierService.getSupplierById(supplier_id);
            model.addAttribute("listSupplier", supplier);

            List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
            model.addAttribute("listProductCategory", productCategoryList);

            return "SupplierDetail/Create";
        }
        return "redirect:/login";
    }

    // save SupplierDetail
    @PostMapping("/save")
    public String save(SupplierDetail supplierDetail, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            supplierDetailService.saveSupplierDetail(supplierDetail);

            redirectAttrs.addFlashAttribute("success_create", "Supplier Detail Successfully Added!");
            return "SupplierDetail/Index";
        }
        return "redirect:/login";
    }

    // view detail SupplierDetail
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            SupplierDetail supplierDetail = supplierDetailService.getSupplierDetailById(id);
            model.addAttribute("supplierDetailObject", supplierDetail);

            return "SupplierDetail/Detail";
        }
        return "redirect:/login";
    }

    // view delete SupplierDetail
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id,
                         RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            this.supplierDetailService.deleteSupplierDetailById(id);

            redirectAttrs.addFlashAttribute("success_deactive", "Supplier Detail Successfully Deactivated!");
            return "SupplierDetail/Delete";
        }
        return "redirect:/login";

    }

    //delete without view
    @RequestMapping("/deleteSupDet/{id}")
    public String deleteSupDet(@PathVariable(value = "id") Integer id,
                               RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            this.supplierDetailService.deleteSupplierDetailById(id);

            redirectAttrs.addFlashAttribute("success_deactive", "Supplier Detail Successfully Deactivated!");
            return "SupplierDetail/Index";
        }
        return "redirect:/login";
    }
}
