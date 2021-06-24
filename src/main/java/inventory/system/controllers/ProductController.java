package inventory.system.controllers;

import inventory.system.entity.Product;
import inventory.system.service.ProductCategoryService;
import inventory.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
