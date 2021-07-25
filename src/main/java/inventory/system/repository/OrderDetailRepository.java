package inventory.system.repository;

import inventory.system.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    @Query("select a from OrderDetail a where a.order_id=:id_order")
    List<OrderDetail> findAllByOrder(@Param("id_order") String id_order);

    @Query("select a from OrderDetail a join Order o on a.order_id = o.id where o.origin_id=:warehouse_id " +
            "and o.origin_type='Gudang' order by o.updated_at desc")
    List<OrderDetail> findAllByWarehouseOrigin(@Param("warehouse_id") String warehouse_id);

    @Query("select a from OrderDetail a join Order o on a.order_id = o.id where o.dest_id=:warehouse_id " +
            "and o.dest_type='Gudang' order by o.updated_at desc")
    List<OrderDetail> findAllByWarehouseDest(@Param("warehouse_id") String warehouse_id);
}
