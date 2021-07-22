package inventory.system.controllers;

import inventory.system.entity.Driver;
import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.entity.Warehouses;
import inventory.system.service.StaffService;
import inventory.system.service.WarehousesService;
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
@RequestMapping("/staff")
public class StaffsController {

    @Autowired
    StaffService staffService;

    @Autowired
    WarehousesService warehouseService;

    //index
    @RequestMapping("/index")
    public String getStaff(Model model, HttpSession httpsession,
                           @SessionAttribute(required=false) LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            List<Staffs> staffsList = staffService.getAllStaff();

            model.addAttribute("listStaff", staffsList);
            return "Staff/Index";
        }
        return "redirect:/login";
    }

    //view create
    @RequestMapping("/create")
    public String viewAddStaff(Model model, HttpSession httpsession,
                               @SessionAttribute(required=false) LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);
            model.addAttribute("staffObject", new Staffs());
            return "Staff/Create";
        }
        model.addAttribute("staffObject", new Staffs());
        return "redirect:/login";

    }


    //save staff
    @PostMapping("/save")
    public String addStaff(@ModelAttribute("current_staff") Staffs currstaff, Staffs staffs, Model model,
                           RedirectAttributes redirectAttrs, HttpSession httpsession,
                           @SessionAttribute(required=false) LoggedUser logged_user){
        if(staffService.isEmailExist(staffs.getEmail())){
            staffService.saveStaff(staffs);

            redirectAttrs.addFlashAttribute("success_create", "Staff Successfully Added!");
            return "redirect:/staff/index";
        }

        model.addAttribute("staffObject", staffs);
        model.addAttribute("email_exist", "Email Exist! Try Another One");

        return "Staff/Create";
    }

    //view edit staff
    @GetMapping("/edit/{id}")
    public String Update(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession,
                         @SessionAttribute(required=false) LoggedUser logged_user){
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(id);
            List<Warehouses> warehouseList = warehouseService.getAllWarehouses();
            model.addAttribute("listWarehouse", warehouseList);
            model.addAttribute("staffObject", staffs);
            return "Staff/Edit";
        }
        return "redirect:/login";

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Staffs staff,
                         BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            staff.setId(id);
            return "Staff/Edit";
        }

        staffService.update(id, staff);

        redirectAttrs.addFlashAttribute("success_update", "Staff Successfully Updated!");
        return "redirect:/staff/index";
    }

    // view detail Staff
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession,
                         @SessionAttribute(required=false) LoggedUser logged_user) {
        if(Session.isLogin(logged_user,httpsession)){
            Staffs staffs = staffService.getStaffById(id);
            model.addAttribute("staffsObject", staffs);

            return "Staff/Detail";
        }
        return "redirect:/login";

    }

    // view delete Staff
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, HttpSession httpsession,
                         @SessionAttribute(required=false) LoggedUser logged_user) {
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
