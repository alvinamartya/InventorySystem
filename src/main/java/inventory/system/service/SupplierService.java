package inventory.system.service;

import inventory.system.entity.Supplier;
import inventory.system.repository.SupplierRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SupplierService {

    @Resource
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier() {
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
        supplierList.sort(
                Comparator
                        .comparing(Supplier::getStatus)
                        .thenComparing(Supplier::getName)
        );
        return supplierList;
    }
    public void saveSupplier(Supplier supplier) {
        supplier.setId(GeneratorId.generateMasterId(getLastCounter()));
        supplier.setStatus("A");
        supplier.setCreated_at(new Date());
        supplier.setCreated_by("Admin");
        supplier.setUpdated_at(new Date());
        supplier.setUpdated_by("Admin");
        supplierRepository.save(supplier);
    }
    private int getLastCounter() {
        List<Supplier> suppliers = getAllSupplier();
        if (suppliers.size() > 0) {
            suppliers.sort(
                    Comparator
                            .comparing(Supplier::getStatus)
                            .thenComparing(Supplier::getName)
            );
            return Integer.parseInt(suppliers.get(suppliers.size() - 1).getId());
        }

        return 0;
    }
    public void update(String id, Supplier supplier) {
        Supplier s = supplierRepository
                .findById(id)
                .orElseThrow(() -> new IllformedLocaleException("Invalid supplier Id: " + id));

        s.setName(supplier.getName());
        s.setAddress(supplier.getAddress());
        s.setPhone(supplier.getPhone());
        s.setUpdated_at(new Date());
        s.setUpdated_by("Admin");

        supplierRepository.save(s);
    }
    public Supplier getSupplierById(String id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        Supplier supplier = null;
        if (optional.isPresent()) {
            supplier = optional.get();
        } else {
            throw new RuntimeException(" Supplier not found for id :: " + id);
        }

        return supplier;
    }
    public void delete(Supplier supplier) {
        supplier.setStatus("D");
        supplier.setUpdated_at(new Date());
        supplier.setUpdated_by("Admin");
        supplierRepository.save(supplier);
    }
    public void activate(Supplier supplier) {
        supplier.setStatus("A");
        supplier.setUpdated_at(new Date());
        supplier.setUpdated_by("Admin");
        supplierRepository.save(supplier);
    }
}
