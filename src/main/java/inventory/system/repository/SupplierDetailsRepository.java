package inventory.system.repository;

import inventory.system.entity.SupplierDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDetailsRepository extends CrudRepository<SupplierDetail, Integer> {
    @Query("select a from SupplierDetail a where a.supplier_id=:supplier_id")
    List<SupplierDetail> findSupplierDetailBySupId(@Param("supplier_id") String supplier_id);
}
