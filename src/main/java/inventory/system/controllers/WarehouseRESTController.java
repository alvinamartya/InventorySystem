package inventory.system.controllers;

import inventory.system.entity.*;
import inventory.system.model.*;
import inventory.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
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

    @Autowired
    ProductService productService;

    @Autowired
    ShelfService shelfService;

    /*Level 2 Pusat - Cabang*/
    @RequestMapping("/api/getCabang")
    public ResponseCustomJSONModel getCabang(@RequestBody WarehouseCustomJSONModel datapost) {
        List<Warehouses> warehouseList = warehouseService.getCabangByPusat(datapost.getId());

        List<WarehouseCustomJSONModel> data = new ArrayList<>();

        for (Warehouses warehouses : warehouseList) {
            WarehouseCustomJSONModel warehouse = new WarehouseCustomJSONModel();
            warehouse.setId(warehouses.getId());
            warehouse.setName(warehouses.getName());
            data.add(warehouse);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Level 2 Retur Cabang - Pusat*/
    @RequestMapping("/api/getPusat")
    public ResponseCustomJSONModel getPusat(@RequestBody WarehouseCustomJSONModel datapost) {
        List<Warehouses> warehouseList = warehouseService.getPusatByCabang(datapost.getId());

        List<WarehouseCustomJSONModel> data = new ArrayList<>();

        for(int k = 0; k<warehouseList.size(); k++){
            WarehouseCustomJSONModel warehouse = new WarehouseCustomJSONModel();
            warehouse.setId(warehouseList.get(k).getId());
            warehouse.setName(warehouseList.get(k).getName());
            data.add(warehouse);
        }
        ResponseCustomJSONModel response = new ResponseCustomJSONModel("Done", data);
        return response;
    }

    /*Level 3 Cabang - Toko*/
    @RequestMapping("/api/getStore")
    public ResponseCustomJSONModel getStore(@RequestBody WarehouseCustomJSONModel datapost) {
        List<Stores> warehouseList = warehouseService.getStoreByCabang(datapost.getId());

        List<WarehouseCustomJSONModel> data = new ArrayList<>();

        for (Stores stores : warehouseList) {
            WarehouseCustomJSONModel warehouse = new WarehouseCustomJSONModel();
            warehouse.setId(Integer.toString(stores.getId()));
            warehouse.setName(stores.getName());
            data.add(warehouse);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Level 2,3 Get Driver*/
    @RequestMapping("/api/getDriver")
    public ResponseCustomJSONModel getDriver(@RequestBody WarehouseCustomJSONModel datapost) {
        List<Driver> driverList = driverService.getDriverByWarehouseAvailable(datapost.getId());

        List<DriverCustomJSONModel> data = new ArrayList<>();

        for (Driver value : driverList) {
            DriverCustomJSONModel driver = new DriverCustomJSONModel();
            driver.setId(Integer.toString(value.getId()));
            driver.setName(value.getName());
            data.add(driver);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Get Product By Supplier*/
    @RequestMapping("/api/getProduct")
    public ResponseCustomJSONModel getProduct(@RequestBody ProductCustomJSONModel datapost) {
        List<Product> productList = productService.getProductBySupplier(datapost.getId());

        List<ProductCustomJSONModel> data = new ArrayList<>();

        for (Product products : productList) {
            ProductCustomJSONModel product = new ProductCustomJSONModel();
            product.setId(Integer.toString(products.getId()));
            product.setName(products.getName());
            data.add(product);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Get Product By Supplier*/
    @RequestMapping("/api/getAllProduct")
    public ResponseCustomJSONModel getAllProduct(@RequestBody ProductCustomJSONModel datapost) {
        List<Product> productList = productService.getAllProduct();

        List<ProductCustomJSONModel> data = new ArrayList<>();

        for (Product products : productList) {
            ProductCustomJSONModel product = new ProductCustomJSONModel();
            product.setId(Integer.toString(products.getId()));
            product.setName(products.getName());
            data.add(product);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Get Shelf By Category*/
    @RequestMapping("/api/getCategorizedShelf")
    public ResponseCustomJSONModel getShelf(@RequestBody ProductCustomJSONModel datapost, HttpSession httpsession,
                                            @SessionAttribute(required = false) LoggedUser logged_user) {
        List<Shelf> shelfList = shelfService.getShelfByCategory(datapost.getId(),logged_user.getWarehouse_id());

        List<ShelfCustomJSONModel> data = new ArrayList<>();

        for (Shelf shelfs : shelfList) {
            ShelfCustomJSONModel product = new ShelfCustomJSONModel();
            product.setId(shelfs.getId());
            product.setName(shelfs.getId());
            product.setTotalShelf(shelfs.getColumns_shelf()*shelfs.getRows_shelf()*shelfs.getQuantity_shelf());
            product.setFilled(shelfService.countFilledShelf(shelfs.getId()));
            product.setEmptyShelf(product.getTotalShelf()- product.getFilled());
            product.setCapacityText(product.getFilled()+"/"+product.getTotalShelf());
            data.add(product);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    @RequestMapping("/api/getCategorizedShelfWithProduct")
    public ResponseCustomJSONModel getShelfWithProd(@RequestBody ProductCustomJSONModel datapost, HttpSession httpsession,
                                            @SessionAttribute(required = false) LoggedUser logged_user) {
        List<ShelfCustomJSONModel> shelfList = shelfService.getShelfByCategoryAndProduct(datapost.getId(),logged_user.getWarehouse_id());

        List<ShelfCustomJSONModel> data = new ArrayList<>();

        for (ShelfCustomJSONModel product : shelfList) {
            data.add(product);
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    @RequestMapping("/api/getCategorizedShelfDest")
    public ResponseCustomJSONModel getShelfDest(@RequestBody ProductCustomJSONModel datapost) {
        List<Shelf> shelfList = shelfService.getShelfByCategory(datapost.getId(),datapost.getName());

        List<ShelfCustomJSONModel> data = new ArrayList<>();

        for (Shelf shelfs : shelfList) {
            ShelfCustomJSONModel product = new ShelfCustomJSONModel();
            product.setId(shelfs.getId());
            product.setName(shelfs.getId());
            product.setTotalShelf(shelfs.getColumns_shelf()*shelfs.getRows_shelf()*shelfs.getQuantity_shelf());
            product.setFilled(shelfService.countFilledShelf(shelfs.getId()));
            product.setEmptyShelf(product.getTotalShelf()- product.getFilled());
            product.setCapacityText(product.getFilled()+"/"+product.getTotalShelf());
            if(product.getEmptyShelf()!=0){
                data.add(product);
            }
        }
        return new ResponseCustomJSONModel("Done", data);
    }

    /*Get Shelf By Category*/
    @RequestMapping("/api/getCategorizedShelfRR")
    public ResponseCustomJSONModel getShelfRR(@RequestBody ProductCustomJSONModel datapost) {
        List<Shelf> shelfList = shelfService.getShelfByCategoryRR(datapost.getId());

        List<ProductCustomJSONModel> data = new ArrayList<>();

        for (Shelf shelfs : shelfList) {
            ProductCustomJSONModel product = new ProductCustomJSONModel();
            product.setId(shelfs.getId());
            product.setName(shelfs.getId());
            data.add(product);
        }
        return new ResponseCustomJSONModel("Done", data);
    }
}
