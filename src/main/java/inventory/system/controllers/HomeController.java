package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.model.DashboardCardAdminMasterModel;
import inventory.system.model.DashboardCardOrderModel;
import inventory.system.model.DashboardCardOrderWarehouseModel;
import inventory.system.model.DashboardCardSuperAdminModel;
import inventory.system.service.DashboardService;
import inventory.system.service.StaffService;
import inventory.system.utils.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Resource
    StaffService staffService;

    @Resource
    DashboardService dashboardService;

    // view index
    @GetMapping("/")
    public String index(Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            switch (logged_user.getRole_id()) {
                case 1:
                    DashboardCardOrderModel dashboardCardTransactionModel = dashboardService.getDashboardCardAdminTransaction(logged_user.getId());
                    model.addAttribute("dashboardObject", dashboardCardTransactionModel);
                    return "Dashboard/IndexTransactionAdmin";
                case 2:
                    DashboardCardOrderWarehouseModel dashboardCardWarehouseModel = dashboardService.getDashboardCardAdminWarehouse(logged_user.getId());
                    model.addAttribute("dashboardObject", dashboardCardWarehouseModel);
                    return "Dashboard/IndexWarehouseAdmin";
                case 3:
                    DashboardCardAdminMasterModel dashboardCardAdminMasterModel = dashboardService.getDashboardCardAdminMaster();
                    model.addAttribute("dashboardObject", dashboardCardAdminMasterModel);
                    return "Dashboard/IndexMasterAdmin";
                case 4:
                    DashboardCardSuperAdminModel dashboardCardSuperAdminModel = dashboardService.getDashboardCardSuperAdmin();
                    DashboardCardAdminMasterModel dashboardCardSuperAdminMasterModel = dashboardService.getDashboardCardAdminMaster();
                    model.addAttribute("dashboardOrderObject", dashboardCardSuperAdminModel);
                    model.addAttribute("dashboardMasterObject", dashboardCardSuperAdminMasterModel);
                    return "Dashboard/IndexSuperAdmin";
                default:
                    return "Dashboard/Index";
            }
        }
        return "redirect:/login";

    }

    @RequestMapping("/settings")
    public String settingview(Model model, HttpSession httpsession,
                              @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Staffs staffs = staffService.getStaffById(logged_user.getId());
            model.addAttribute("staffObject", staffs);
            switch (logged_user.getRole_id()) {
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
                                 HttpSession httpsession, @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            Staffs staffs = staffService.getStaffById(staff.getId());
            staffService.sendEmailUpdatePassword(staffs);
            redirectAttrs.addFlashAttribute("success_create", "Email Has Been Sent!");
            return "redirect:/settings";
        }
        return "redirect:/login";
    }
}
