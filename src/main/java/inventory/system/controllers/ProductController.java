package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Product;
import inventory.system.entity.ProductCategory;
import inventory.system.entity.Supplier;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    // view index
    @RequestMapping("/index")
    public String index(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("listProduct", productList);
        return "Product/Index";
        }
        return "redirect:/login";
    }

    // view create
    @RequestMapping("/create")
    public String create(Model model, HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        model.addAttribute("productObject", new Product());

        List<String> unitList = productService.getUnits();
        List<ProductCategory> categoryList = productCategoryService.getAllProductCategory();
        model.addAttribute("listUnit", unitList);
        model.addAttribute("listCategory", categoryList);

        return "Product/Create";
        }
        return "redirect:/login";
    }

    // save product
    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        productService.saveProduct(product, logged_user);
        redirectAttrs.addFlashAttribute("success_create", "Product Successfully Added!");

        return "redirect:/product/index";
        }
        return "redirect:/login";
    }

    // view edit product
    @GetMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") int id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        List<String> unitList = productService.getUnits();
        List<ProductCategory> categoryList = productCategoryService.getAllProductCategory();
        model.addAttribute("listUnit", unitList);
        model.addAttribute("listCategory", categoryList);

        return "Product/Edit";
        }
        return "redirect:/login";
    }

    // update product
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Product product, BindingResult result,
                         RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        if(result.hasErrors()) {
            product.setId(id);
            return "Product/Edit";
        }

        productService.update(id, product, logged_user);

        redirectAttrs.addFlashAttribute("success_update", "Product Successfully Updated!");
        return "redirect:/product/index";
        }
        return "redirect:/login";
    }

    // view detail product
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") int id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Detail";
        }
        return "redirect:/login";
    }

    // view delete product
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
        }
        return "redirect:/login";
    }

    // confirm to delete product
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        productService.delete(product, logged_user);

        redirectAttrs.addFlashAttribute("success_deactive", "Product Successfully Deactivated!");
        return "redirect:/product/index";
        }
        return "redirect:/login";
    }

    // deactivate product
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") int id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
        }
        return "redirect:/login";
    }

    // activated product
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") int id, Model model
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
        }
        return "redirect:/login";
    }

    // activate status product
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs
            , HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
        Product product = productService.getProductById(id);
        productService.activate(product, logged_user);

        redirectAttrs.addFlashAttribute("success_active", "Product Successfully Activated!");
        return "redirect:/product/index";
        }
        return "redirect:/login";
    }
}
