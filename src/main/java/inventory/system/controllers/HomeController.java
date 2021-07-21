package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    // view index
    @GetMapping("/")
    public String index(@SessionAttribute("logged_user") LoggedUser loggedUser, final Model model) {
        model.addAttribute("logged_user", loggedUser);
        return "Dashboard/Index";
    }
}
