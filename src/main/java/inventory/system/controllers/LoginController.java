package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.service.LoginService;
import inventory.system.service.StaffService;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("logged_user")
public class LoginController {
    @Autowired
    StaffService staffService;

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String login(HttpSession httpsession, @ModelAttribute LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            if(logged_user.getRole_id().equals(1)){
                return "redirect:/";
            }
            else if(logged_user.getRole_id().equals(2)){
                return "redirect:/";
            }
            else if(logged_user.getRole_id().equals(3)){
                return "redirect:/";
            }

            return "Login/Index";
        }
        return "Login/Index";
    }

    @PostMapping("/login/authentication")
    public String authentication
            (final Model model, @ModelAttribute LoggedUser logged_user, Staffs staff, RedirectAttributes redirectAttrs) {

        String email = staff.getEmail();

        if(loginService.checkEmail(email)){
            Staffs staffData = loginService.getStaff(email);
            if(loginService.authentication(staffData.getPassword(), staff.getPassword())){
                LoggedUser data = new LoggedUser();
                data.setId(staffData.getId());
                data.setName(staffData.getName());
                data.setRole_id(staffData.getRole_id());
                switch(staffData.getRole_id()) {
                    case 1:
                        data.setRole_name("Transaction Admin");
                        break;
                    case 2:
                        data.setRole_name("Warehouse Admin");
                        break;
                    case 3:
                        data.setRole_name("Master Data Admin");
                        break;
                    case 4:
                        data.setRole_name("*Super Admin*");
                        break;
                }
                data.setStatus(staffData.getStatus());
                data.setWarehouse_id(staffData.getWarehouse_id());
                data.setWarehouse_name(staffData.getWarehousesList().getName());
                data.setIs_branch(staffData.getWarehousesList().getIs_branch());
                model.addAttribute("logged_user", data);
                return "redirect:/";
            }
            else{
                redirectAttrs.addFlashAttribute("wrong_password", "Password Not Match!");
                return "redirect:/login";
            }
        }
        else{
            redirectAttrs.addFlashAttribute("wrong_email", "Email Not Found!");
            return "redirect:/login";
        }
    }

    @ModelAttribute("logged_user")
    public LoggedUser logged_user() {
        return new LoggedUser();
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpsession, SessionStatus status) {
        /*Mark the current handler's session processing as complete, allowing for cleanup of
        session attributes.*/
        status.setComplete();

        /* Invalidates this session then unbinds any objects boundto it. */
        httpsession.invalidate();
        return "redirect:/login";
    }
}
