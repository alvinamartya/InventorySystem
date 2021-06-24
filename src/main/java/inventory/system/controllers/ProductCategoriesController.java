package inventory.system.controllers;

import inventory.system.entity.ProductCategory;
import inventory.system.service.ProductCategoryService;
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
@RequestMapping("/product-category")
public class ProductCategoriesController {

    @Autowired
    ProductCategoryService productCategoryService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
        model.addAttribute("listProductCategory", productCategoryList);
        return "ProductCategory/Index";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("productCategoryObject", new ProductCategory());
        return "ProductCategory/Create";
    }

    // save product category
    @PostMapping("/save")
    public String save(ProductCategory productCategory, RedirectAttributes redirectAttrs) {
        productCategoryService.saveProductCategory(productCategory);
        redirectAttrs.addFlashAttribute("success_create", "Product Category Successfully Added!");
        return "redirect:/product-category/index";
    }

    // view edit product category
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategoryObject", productCategory);

        return "ProductCategory/Edit";
    }

    // update product category
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, ProductCategory productCategory, BindingResult result, RedirectAttributes redirectAttrs) {
        if(result.hasErrors()) {
            productCategory.setId(id);
            return "ProductCategory/Edit";
        }

        productCategoryService.update(id, productCategory);
        redirectAttrs.addFlashAttribute("success_update", "Product Category Successfully Updated!");
        return "redirec:/product-category/index";
    }

    // view detail product category
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategoryObject", productCategory);

        return "ProductCategory/Detail";
    }

    // view delete product category
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategoryObject", productCategory);

        return "ProductCategory/Delete";
    }

    // confirm to delete product category
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        productCategoryService.delete(productCategory);

        redirectAttrs.addFlashAttribute("success_deactive", "Product Category Successfully Deactivated!");
        return "redirect:/product-category/index";
    }

    // deactivate driver
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategoryObject", productCategory);

        return "ProductCategory/Delete";
    }

    // activated driver
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategoryObject", productCategory);

        return "ProductCategory/Delete";
    }

    // confirm to active product category
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        productCategoryService.activate(productCategory);

        redirectAttrs.addFlashAttribute("success_deactive", "Product Category Successfully Activated!");
        return "redirect:/product-category/index";
    }
}
