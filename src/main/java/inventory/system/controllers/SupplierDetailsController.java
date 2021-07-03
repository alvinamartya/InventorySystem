package inventory.system.controllers;

import inventory.system.entity.ProductCategory;
import inventory.system.entity.Supplier;
import inventory.system.entity.SupplierDetail;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.SupplierDetailService;
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
    public String index(Model model, @PathVariable(value = "id") String supplier_id) {
        model.addAttribute("supplierDetailObject", supplier_id);

        List<SupplierDetail> supplierDetailList = supplierDetailService.getSupplierDetailByIdSup(supplier_id);
        model.addAttribute("listSupDetail", supplierDetailList);

        return "SupplierDetail/Index";
    }

    // view create
    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable(value = "id") String supplier_id) {
        model.addAttribute("supplierDetailObject", new SupplierDetail());

        Supplier supplier = supplierService.getSupplierById(supplier_id);
        model.addAttribute("listSupplier", supplier);

        List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
        model.addAttribute("listProductCategory", productCategoryList);

        return "SupplierDetail/Create";
    }

    // save SupplierDetail
    @PostMapping("/save")
    public String save(SupplierDetail supplierDetail, String supplier_id, RedirectAttributes redirectAttrs) {
        supplierDetailService.saveSupplierDetail(supplierDetail, supplier_id);

        redirectAttrs.addFlashAttribute("success_create", "Supplier Detail Successfully Added!");
        return "SupplierDetail/Index";
    }

    // view detail SupplierDetail
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model) {
        SupplierDetail supplierDetail = supplierDetailService.getSupplierDetailById(id);
        model.addAttribute("supplierDetailObject", supplierDetail);

        return "SupplierDetail/Detail";
    }

    // view delete SupplierDetail
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id,
                         RedirectAttributes redirectAttrs) {
        this.supplierDetailService.deleteSupplierDetailById(id);

        redirectAttrs.addFlashAttribute("success_deactive", "Supplier Detail Successfully Deactivated!");
        return "SupplierDetail/Delete";
    }

    //delete without view
    @RequestMapping("/deleteSupDet/{id}")
    public String deleteSupDet(@PathVariable(value = "id") Integer id,
                         RedirectAttributes redirectAttrs) {
        this.supplierDetailService.deleteSupplierDetailById(id);

        redirectAttrs.addFlashAttribute("success_deactive", "Supplier Detail Successfully Deactivated!");
        return "SupplierDetail/Index";
    }
}
