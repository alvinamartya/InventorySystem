package inventory.system.controllers;

import inventory.system.entity.Product;
import inventory.system.entity.ProductCategory;
import inventory.system.entity.Supplier;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    ProductCategoryService productCategoryService;

    // view index
    @RequestMapping("/index")
    public String index(Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("listProduct", productList);
        return "Product/Index";
    }

    // view detail product
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Detail";
    }

    // view delete product
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
    }

    // confirm to delete product
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Product product = productService.getProductById(id);
        productService.delete(product);

        redirectAttrs.addFlashAttribute("success_deactive", "Product Successfully Deactivated!");
        return "redirect:/product/index";
    }

    // deactivate product
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
    }

    // activated product
    @GetMapping("/activate/{id}")
    public String active(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("productObject", product);

        return "Product/Delete";
    }

    // activate status product
    @PostMapping("/activate-confirmed/{id}")
    public String activateConfirmed(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Product product = productService.getProductById(id);
        productService.activate(product);

        redirectAttrs.addFlashAttribute("success_active", "Product Successfully Activated!");
        return "redirect:/product/index";
    }

}
