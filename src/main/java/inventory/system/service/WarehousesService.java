package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Stores;
import inventory.system.entity.Warehouses;
import inventory.system.repository.StoresRepository;
import inventory.system.entity.*;
import inventory.system.repository.WarehousesRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WarehousesService {

    @Autowired
    WarehousesRepository warehousesRepository;

    @Autowired
    StoresRepository storesRepository;

    public List<Warehouses> getAllWarehouses() {
        List<Warehouses> warehousesList = (List<Warehouses>) warehousesRepository.findAll();
        warehousesList.sort(
                Comparator
                        .comparing(Warehouses::getStatus)
                        .thenComparing(Warehouses::getIs_branch)
                        .thenComparing(Warehouses::getName)
        );
        return warehousesList;
    }

    public List<Warehouses> getAllWarehousesPusat() {
        return warehousesRepository.findAllPusat();
    }

    public List<Warehouses> getAllWarehousesCabang() {
        return warehousesRepository.findAllCabang();
    }

    public List<Warehouses> getWarehousesPusatById(String id){
        return warehousesRepository.findPusatById(id);
    }

    public List<Warehouses> getCabangByPusat(String id) {
        Warehouses selected = getWarehousesById(id);
        return warehousesRepository.findCabangByPusat(selected.getProvince());
    }

    public List<Warehouses> getPusatByCabang(String id) {
        Warehouses selected = getWarehousesById(id);
        List<Warehouses> warehousesList = warehousesRepository.findPusatByCabang(selected.getProvince());

        return warehousesList;
    }

    public List<Warehouses> getCabangByStore(String id) {
        Warehouses selected = getWarehousesById(id);
        List<Warehouses> warehousesList = warehousesRepository.findCabangByStore(selected.getCity());

        return warehousesList;
    }

    public List<Stores> getStoreByCabang(String id) {
        Warehouses selected = getWarehousesById(id);
        return storesRepository.findStoreByCabang(selected.getCity());
    }

    public List<Driver> getDriverByWarehouse(String id) {
        Warehouses selected = getWarehousesById(id);
        List<Driver> driverList = warehousesRepository.findDriverByCabang(selected.getId());

        return driverList;
    }

    public void saveWarehouses(Warehouses warehouses, LoggedUser loggedUser) {
        warehouses.setId(GeneratorId.generateMasterId(getLastCounter()));
        warehouses.setStatus("A");
        warehouses.setCreated_at(new Date());
        warehouses.setUpdated_at(new Date());
        warehouses.setCreated_by(loggedUser.getName());
        warehouses.setUpdated_by(loggedUser.getName());
        warehousesRepository.save(warehouses);
    }

    private int getLastCounter() {
        List<Warehouses> warehouses = getAllWarehouses();
        if (warehouses.size() > 0) {
            warehouses.sort(Comparator.comparing(Warehouses::getId));
            return Integer.parseInt(warehouses.get(warehouses.size() - 1).getId());
        }

        return 0;
    }

    public int update(String id, Warehouses warehouse, LoggedUser loggedUser) {
        Warehouses warehouses = warehousesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));
        warehouses.setName(warehouse.getName());
        warehouses.setCity(warehouse.getCity());
        warehouses.setProvince(warehouse.getProvince());
        warehouses.setIs_branch(warehouse.getIs_branch());
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by(loggedUser.getName());
        warehousesRepository.save(warehouses);

        return 1;
    }

    public Warehouses getWarehousesById(String id) {
        Optional<Warehouses> optional = warehousesRepository.findById(id);
        Warehouses warehouses = null;
        if (optional.isPresent()) {
            warehouses = optional.get();
        } else {
            throw new RuntimeException(" Warehouses not found for id :: " + id);
        }
        return warehouses;
    }

    public void deleteWarehouses(Warehouses warehouses, LoggedUser loggedUser) {
        warehouses.setStatus("D");
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by(loggedUser.getName());
        warehousesRepository.save(warehouses);
    }

    public int activate(Warehouses warehouses, LoggedUser loggedUser) {
        warehouses.setStatus("A");
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by(loggedUser.getName());
        warehousesRepository.save(warehouses);

        return 1;
    }
}
