package inventory.system.service;

import inventory.system.entity.SupplierDetail;
import inventory.system.repository.SupplierDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierDetailService {
    @Autowired
    SupplierDetailsRepository supplierDetailsRepository;

    public List<SupplierDetail> getAllSupplierDetail() {
        List<SupplierDetail> supplierDetailList = (List<SupplierDetail>) supplierDetailsRepository.findAll();
        supplierDetailList.sort(
                Comparator
                        .comparing(SupplierDetail::getSupplier_id)
                        .thenComparing(SupplierDetail::getProduct_category_id)
        );
        return supplierDetailList;
    }

    public List<SupplierDetail> getSupplierDetailByIdSup(String supplier_id) {
        List<SupplierDetail> supplierDetailList =
                (List<SupplierDetail>) supplierDetailsRepository.findSupplierDetailBySupId(supplier_id);

        return supplierDetailList;
    }

    public List<SupplierDetail> saveSupplierDetail(SupplierDetail supplierDetail, String supplier_id) {
        supplierDetailsRepository.save(supplierDetail);

        return getAllSupplierDetail();
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
