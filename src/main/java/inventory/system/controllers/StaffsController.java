package inventory.system.controllers;

import inventory.system.entity.Staffs;
import inventory.system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffsController {

    @Autowired
    StaffService staffService;

    //index
    @RequestMapping("/index")
    public String getStaff(Model model){
        List<Staffs> staffsList = staffService.getAllStaff();
        model.addAttribute("listStaff", staffsList);
        return "Staff/Index";
    }

    //view create
    @RequestMapping("/create")
    public String viewAddStaff(Model model){
        model.addAttribute("staffObject", new Staffs());
        return "Staff/Create";
    }

    //save staff
    @PostMapping("/save")
    public String addStaff(Staffs staffs){
        staffService.saveStaff(staffs);
        return "redirect:/staff/index";
    }

    //view edit staff
    @GetMapping("/edit/{id}")
    public String Update(@PathVariable(value = "id") Integer id, Model model){
        Staffs staffs = staffService.getStaffById(id);

        model.addAttribute("staffObject", staffs);
        return "Staff/Edit";
    }

    // view detail Staff
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model) {
        Staffs staffs = staffService.getStaffById(id);
        model.addAttribute("staffsObject", staffs);

        return "Staff/Detail";
    }

    // view delete Staff
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model) {
        Staffs staffs = staffService.getStaffById(id);
        model.addAttribute("staffsObject", staffs);

        return "Staff/Delete";
    }

    // confirm to delete Staff
    @PostMapping("/delete-confirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") int id) {
        Staffs staffs = staffService.getStaffById(id);

        staffService.deleteStaff(staffs);
        return "redirect:/staff/index";
    }


}
