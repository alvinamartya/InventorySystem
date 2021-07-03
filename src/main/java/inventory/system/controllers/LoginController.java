package inventory.system.controllers;

import inventory.system.entity.Driver;
import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.service.DriverService;
import inventory.system.service.LoginService;
import inventory.system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("logged_user")
public class LoginController {
    @Autowired
    StaffService staffService;

    @Autowired
    LoginService loginService;

    public boolean isLogin(LoggedUser logged_user, HttpSession httpsession){
        if(httpsession.getAttribute("logged_user")==null || logged_user.getId()==null){
            return false;
        }
        return true;
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession httpsession, @ModelAttribute LoggedUser logged_user) {
        if(isLogin(logged_user,httpsession)){
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
                data.setStatus(staffData.getStatus());
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