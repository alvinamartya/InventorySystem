package inventory.system.repository;

import inventory.system.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {
    @Query("select a from Order a where a.warehouse_at=:warehouse_id " +
            "AND a.origin_type='Pemasok' AND a.dest_type='Gudang' order by a.updated_at desc")
    List<Order> findByWarehouseLv1(@Param("warehouse_id") String warehouse_id);

    @Query("select a from Order a where a.warehouse_at=:warehouse_id " +
            "AND a.origin_type='Gudang' AND a.dest_type='Gudang' order by a.updated_at desc")
    List<Order> findByWarehouseLv2(@Param("warehouse_id") String warehouse_id);

    @Query("select a from Order a where a.warehouse_at=:warehouse_id " +
            "AND a.origin_type='Gudang' AND a.dest_type='Toko' order by a.updated_at desc")
    List<Order> findByWarehouseLv3(@Param("warehouse_id") String warehouse_id);
}
