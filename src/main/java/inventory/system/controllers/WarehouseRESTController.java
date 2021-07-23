package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.service.DriverService;
import inventory.system.service.StoresService;
import inventory.system.service.WarehousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WarehouseRESTController {
    @Autowired
    WarehousesService warehouseService;

    @Autowired
    StoresService storeService;

    @Autowired
    DriverService driverService;

    /*Level 2 Pusat - Cabang*/
    @RequestMapping("/api/getCabang")
    public ResponseCustomJSON getCabang(@RequestBody WarehouseCustomJSON datapost) {
        List<Warehouses> warehouseList = warehouseService.getCabangByPusat(datapost.getId());

        List<WarehouseCustomJSON> data = new ArrayList<>();

        for(int k = 0; k<warehouseList.size(); k++){
            WarehouseCustomJSON warehouse = new WarehouseCustomJSON();
            warehouse.setId(warehouseList.get(k).getId());
            warehouse.setName(warehouseList.get(k).getName());
            data.add(warehouse);
        }
        ResponseCustomJSON response = new ResponseCustomJSON("Done", data);
        return response;
    }

    /*Level 3 Cabang - Toko*/
    @RequestMapping("/api/getStore")
    public ResponseCustomJSON getStore(@RequestBody WarehouseCustomJSON datapost) {
        List<Stores> warehouseList = warehouseService.getStoreByCabang(datapost.getId());

        List<WarehouseCustomJSON> data = new ArrayList<>();

        for(int k = 0; k<warehouseList.size(); k++){
            WarehouseCustomJSON warehouse = new WarehouseCustomJSON();
            warehouse.setId(Integer.toString(warehouseList.get(k).getId()));
            warehouse.setName(warehouseList.get(k).getName());
            data.add(warehouse);
        }
        ResponseCustomJSON response = new ResponseCustomJSON("Done", data);
        return response;
    }

    /*Level 2,3 Get Driver*/
    @RequestMapping("/api/getDriver")
    public ResponseCustomJSON getDriver(@RequestBody WarehouseCustomJSON datapost) {
        List<Driver> driverList = driverService.getDriverByWarehouse(datapost.getId());

        List<DriverCustomJSON> data = new ArrayList<>();

        for(int k = 0; k<driverList.size(); k++){
            DriverCustomJSON driver = new DriverCustomJSON();
            driver.setId(Integer.toString(driverList.get(k).getId()));
            driver.setName(driverList.get(k).getName());
            data.add(driver);
        }
        ResponseCustomJSON response = new ResponseCustomJSON("Done", data);
        return response;
    }
}
