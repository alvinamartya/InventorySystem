package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.service.StaffService;
import inventory.system.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffsController {

    @Autowired
    StaffService staffService;

    //index
    @RequestMapping("/index")
    public String getStaff(Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            List<Staffs> staffsList = staffService.getAllStaff();

            model.addAttribute("listStaff", staffsList);
            return "Staff/Index";
        }
        return "redirect:/login";
    }

    //view create
    /*@RequestMapping("/create")
    public String viewAddStaff(Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            model.addAttribute("staffObject", new Staffs());
            return "Staff/Create";
        }
        model.addAttribute("staffObject", new Staffs());
        return "Staff/Create";
        return "redirect:/login";

    }*/

    @RequestMapping("/create")
    public String viewAddStaff(Model model){
        model.addAttribute("staffObject", new Staffs());
        return "Staff/Create";

    }

    //save staff
    /*@PostMapping("/save")
    public String addStaff(@ModelAttribute("current_staff") Staffs currstaff, Staffs staffs, Model model, RedirectAttributes redirectAttrs, HttpSession httpsession, @SessionAttribute LoggedUser logged_user){
        if(staffService.isEmailExist(staffs.getEmail())){
            staffService.saveStaff(staffs);

            redirectAttrs.addFlashAttribute("success_create", "Staff Successfully Added!");
            return "redirect:/staff/index";
        }

        model.addAttribute("staffObject", staffs);
        model.addAttribute("email_exist", "Email Exist! Try Another One");

        return "Staff/Create";
    }*/

    @PostMapping("/save")
    public String addStaff(@ModelAttribute("current_staff") Staffs currstaff, Staffs staffs, Model model, RedirectAttributes redirectAttrs){

            staffService.saveStaff(staffs);

            redirectAttrs.addFlashAttribute("success_create", "Staff Successfully Added!");
            return "redirect:/staff/index";

    }

    //view edit staff
    @GetMapping("/edit/{id}")
    public String Update(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(id);

            model.addAttribute("staffObject", staffs);
            return "Staff/Edit";
        }
        return "redirect:/login";

    }

    // view detail Staff
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(id);
            model.addAttribute("staffsObject", staffs);

            return "Staff/Detail";
        }
        return "redirect:/login";

    }

    // view delete Staff
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession, @SessionAttribute LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(id);
            model.addAttribute("staffsObject", staffs);

            return "Staff/Delete";
        }
        return "redirect:/login";

    }

    // confirm to delete Staff
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id) {
        Staffs staffs = staffService.getStaffById(id);

        staffService.deleteStaff(staffs);
        return "redirect:/staff/index";
    }
}
