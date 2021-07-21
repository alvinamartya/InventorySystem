package inventory.system.controllers;

import inventory.system.entity.ResponseCustomJSON;
import inventory.system.entity.Stores;
import inventory.system.entity.WarehouseCustomJSON;
import inventory.system.entity.Warehouses;
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
}