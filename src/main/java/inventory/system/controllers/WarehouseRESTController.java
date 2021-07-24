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

        for (Warehouses warehouses : warehouseList) {
            WarehouseCustomJSON warehouse = new WarehouseCustomJSON();
            warehouse.setId(warehouses.getId());
            warehouse.setName(warehouses.getName());
            data.add(warehouse);
        }
        return new ResponseCustomJSON("Done", data);
    }

    /*Level 3 Cabang - Toko*/
    @RequestMapping("/api/getStore")
    public ResponseCustomJSON getStore(@RequestBody WarehouseCustomJSON datapost) {
        List<Stores> warehouseList = warehouseService.getStoreByCabang(datapost.getId());

        List<WarehouseCustomJSON> data = new ArrayList<>();

        for (Stores stores : warehouseList) {
            WarehouseCustomJSON warehouse = new WarehouseCustomJSON();
            warehouse.setId(Integer.toString(stores.getId()));
            warehouse.setName(stores.getName());
            data.add(warehouse);
        }
        return new ResponseCustomJSON("Done", data);
    }

    /*Level 2,3 Get Driver*/
    @RequestMapping("/api/getDriver")
    public ResponseCustomJSON getDriver(@RequestBody WarehouseCustomJSON datapost) {
        List<Driver> driverList = driverService.getDriverByWarehouse(datapost.getId());

        List<DriverCustomJSON> data = new ArrayList<>();

        for (Driver value : driverList) {
            DriverCustomJSON driver = new DriverCustomJSON();
            driver.setId(Integer.toString(value.getId()));
            driver.setName(value.getName());
            data.add(driver);
        }
        return new ResponseCustomJSON("Done", data);
    }
}
