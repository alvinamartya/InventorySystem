package inventory.system.service;

import inventory.system.entity.Warehouses;
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

    public List<Warehouses> getAllWarehousesPusat(){
        return warehousesRepository.findAllPusat();
    }

    public List<Warehouses> getAllWarehousesCabang(){
        return warehousesRepository.findAllCabang();
    }

    public void saveWarehouses(Warehouses warehouses) {
        warehouses.setId(GeneratorId.generateMasterId(getLastCounter()));
        warehouses.setStatus("A");
        warehouses.setCreated_at(new Date());
        warehouses.setUpdated_at(new Date());
        warehouses.setCreated_by("Admin");
        warehouses.setUpdated_by("Admin");
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

    public int update(String id, Warehouses warehouse) {
        Warehouses warehouses = warehousesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse Id:" + id));
        warehouses.setName(warehouse.getName());
        warehouses.setCity(warehouse.getCity());
        warehouses.setProvince(warehouse.getProvince());
        warehouses.setIs_branch(warehouse.getIs_branch());
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by("Admin");
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

    public void deleteWarehouses(Warehouses warehouses) {
        warehouses.setStatus("D");
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by("Admin");
        warehousesRepository.save(warehouses);
    }

    public int activate(Warehouses warehouses) {
        warehouses.setStatus("A");
        warehouses.setUpdated_at(new Date());
        warehouses.setUpdated_by("Admin");
        warehousesRepository.save(warehouses);

        return 1;
    }
}
