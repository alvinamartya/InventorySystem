package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.utils.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    // view index
    @GetMapping("/")
    public String index(Model model, HttpSession httpsession,
                        @SessionAttribute(required=false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){

            switch(logged_user.getRole_id()) {
                case 1:
                    return "Dashboard/IndexTransactionAdmin";
                case 2:
                    return "Dashboard/IndexWarehouseAdmin";
                case 3:
                    return "Dashboard/IndexMasterAdmin";
                case 4:
                    return "Dashboard/IndexSuperAdmin";
                default:
                    return "Dashboard/Index";
            }
        }
        return "redirect:/login";

    }
}
