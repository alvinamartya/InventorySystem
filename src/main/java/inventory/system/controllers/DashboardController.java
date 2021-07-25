package inventory.system.controllers;

import inventory.system.model.DashboardChartModel;
import inventory.system.service.DashboardService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DashboardController {
    @Resource
    private DashboardService dashboardService;

    @GetMapping("/dashboard-order-in")
    public List<DashboardChartModel> getDashboardOrderInChart(@RequestParam String warehouseId) {
        return dashboardService.getDashboardOrderInByWarehouseId(warehouseId);
    }

    @GetMapping("/dashboard-order-out")
    public List<DashboardChartModel> getDashboardOrderOutChart(@RequestParam String warehouseId) {
        return dashboardService.getDashboardOrderOutByWarehouseId(warehouseId);
    }

    @GetMapping("/dashboard-order")
    public List<DashboardChartModel> getDashboardOrderChart() {
        return dashboardService.getDashboardOrderAll();
    }

    @GetMapping("/dashboard-retur")
    public List<DashboardChartModel> getDashboardReturChart() {
        return dashboardService.getDashboardReturAll();
    }
}
