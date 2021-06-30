package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    // view index
    @GetMapping("/")
    public String index(@SessionAttribute("logged_user") LoggedUser loggedUser, final Model model) {
        model.addAttribute("logged_user", loggedUser);
        return "Dashboard/Index";
    }

}