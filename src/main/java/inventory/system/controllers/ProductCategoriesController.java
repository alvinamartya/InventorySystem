package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.ProductCategory;
import inventory.system.service.ProductCategoryService;
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
@RequestMapping("/product-category")
public class ProductCategoriesController {

    @Autowired
    ProductCategoryService productCategoryService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            List<ProductCategory> productCategoryList = productCategoryService.getAllProductCategory();
            model.addAttribute("listProductCategory", productCategoryList);
            return "ProductCategory/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            model.addAttribute("productCategoryObject", new ProductCategory());
            return "ProductCategory/Create";
        }
        return "redirect:/login";
    }

    // save product category
    @PostMapping("/save")
    public String save(ProductCategory productCategory, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            productCategoryService.saveProductCategory(productCategory, logged_user);
            redirectAttrs.addFlashAttribute("success_create", "Product Category Successfully Added!");
            return "redirect:/product-category/index";
        }
        return "redirect:/login";
    }

    // view edit product category
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            model.addAttribute("productCategoryObject", productCategory);

            return "ProductCategory/Edit";
        }
        return "redirect:/login";
    }

    // update product category
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, ProductCategory productCategory,
                         BindingResult result, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            if (result.hasErrors()) {
                productCategory.setId(id);
                return "ProductCategory/Edit";
            }

            productCategoryService.update(id, productCategory);
            redirectAttrs.addFlashAttribute("success_update", "Product Category Successfully Updated!");
            return "redirec:/product-category/index";
        }
        return "redirect:/login";
    }

    // view detail product category
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            model.addAttribute("productCategoryObject", productCategory);

            return "ProductCategory/Detail";
        }
        return "redirect:/login";
    }

    // view delete product category
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            model.addAttribute("productCategoryObject", productCategory);

            return "ProductCategory/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete product category
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            productCategoryService.delete(productCategory, logged_user);

            redirectAttrs.addFlashAttribute("success_deactive", "Product Category Successfully Deactivated!");
            return "redirect:/product-category/index";
        }
        return "redirect:/login";
    }

    // deactivate driver
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            model.addAttribute("productCategoryObject", productCategory);

            return "ProductCategory/Delete";
        }
        return "redirect:/login";
    }

    // activated driver
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") String id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            model.addAttribute("productCategoryObject", productCategory);

            return "ProductCategory/Delete";
        }
        return "redirect:/login";
    }

    // confirm to active product category
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") String id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if (Session.isLogin(logged_user, httpsession)) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            productCategoryService.activate(productCategory, logged_user);

            redirectAttrs.addFlashAttribute("success_deactive", "Product Category Successfully Activated!");
            return "redirect:/product-category/index";
        }
        return "redirect:/login";
    }
}
