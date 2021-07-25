package inventory.system.controllers;

import inventory.system.entity.LoggedUser;
import inventory.system.model.MonitoringOrderModel;
import inventory.system.model.MonitoringReturModel;
import inventory.system.service.MonitoringService;
import inventory.system.utils.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MonitoringController {

    @Resource
    private MonitoringService monitoringService;

    @GetMapping("/monitoring-order")
    public String indexMonitoringOrder(Model model, HttpSession httpsession,
                        @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<MonitoringOrderModel> monitoringOrderList = monitoringService.getMonitoringModel(logged_user.getId());
            model.addAttribute("listMonitoringOrder", monitoringOrderList);
            return "Monitoring/IndexMonitoringOrder";
        }
        return "redirect:/login";
    }

    @GetMapping("/monitoring-retur")
    public String indexMonitorinRetur(Model model, HttpSession httpsession,
                                       @SessionAttribute(required = false) LoggedUser logged_user) {
        if(logged_user == null || httpsession == null) {
            return "redirect:/login";
        } else if (Session.isLogin(logged_user, httpsession)) {
            List<MonitoringReturModel> monitoringReturList = monitoringService.getMonitoringReturModel(logged_user.getId());
            model.addAttribute("listMonitoringRetur", monitoringReturList);
            return "Monitoring/IndexMonitoringRetur";
        }
        return "redirect:/login";
    }
}
