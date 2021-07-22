package inventory.system.repository;

import inventory.system.entity.Order;
import inventory.system.entity.Shelf;
import inventory.system.entity.Stores;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {
    @Query("select a from Order a where a.warehouse_at=:warehouse_id " +
            "AND a.origin_type='Pemasok' AND a.dest_type='Gudang' ")
    public List<Order> findByWarehouse(@Param("warehouse_id") String warehouse_id);
}
