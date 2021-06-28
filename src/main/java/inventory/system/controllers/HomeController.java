package inventory.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    // view index
    @RequestMapping("/")
    public String index(Model model) {
        return "Dashboard/Index";
    }
}
