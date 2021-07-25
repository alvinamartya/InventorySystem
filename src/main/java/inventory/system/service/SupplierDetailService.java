package inventory.system.service;

import inventory.system.entity.SupplierDetail;
import inventory.system.repository.SupplierDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierDetailService {
    @Autowired
    SupplierDetailsRepository supplierDetailsRepository;

    public List<SupplierDetail> getSupplierDetailByIdSup(String supplier_id) {
        return supplierDetailsRepository.findSupplierDetailBySupId(supplier_id);
    }

    public void saveSupplierDetail(SupplierDetail supplierDetail) {
        supplierDetailsRepository.save(supplierDetail);
    }

    public SupplierDetail getSupplierDetailById(Integer id) {
        Optional<SupplierDetail> optional = supplierDetailsRepository.findById(id);
        SupplierDetail supplierDetail = null;
        if (optional.isPresent()) {
            supplierDetail = optional.get();
        } else {
            throw new RuntimeException(" Supplier Detail not found for id :: " + id);
        }
        return supplierDetail;
    }

    public void deleteSupplierDetailById(Integer id) {
        supplierDetailsRepository.deleteById(id);
    }
}
