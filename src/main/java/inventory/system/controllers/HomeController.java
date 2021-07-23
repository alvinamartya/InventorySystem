package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.service.StaffService;
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
public class HomeController {
    @Autowired
    StaffService staffService;

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

    @RequestMapping("/settings")
    public String settingview(Model model, HttpSession httpsession,
                              @SessionAttribute(required=false) LoggedUser logged_user) {

        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(logged_user.getId());
            model.addAttribute("staffObject", staffs);
            switch(logged_user.getRole_id()) {
                case 1:
                    return "Setting/SettingTransactionAdmin";
                case 2:
                    return "Setting/SettingWarehouseAdmin";
                case 3:
                    return "Setting/SettingMasterAdmin";
                case 4:
                    return "Setting/SettingSuperAdmin";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/requestupdatepassword")
    public String updatepassword(Staffs staff, RedirectAttributes redirectAttrs,
                                 HttpSession httpsession,@SessionAttribute(required=false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(staff.getId());
            staffService.sendEmailUpdatePassword(staffs);
            redirectAttrs.addFlashAttribute("success_create", "Email Has Been Sent!");
            return "redirect:/settings";
        }
        return "redirect:/login";
    }
}
