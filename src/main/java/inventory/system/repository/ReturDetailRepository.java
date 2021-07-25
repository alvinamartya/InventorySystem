package inventory.system.repository;

import inventory.system.entity.OrderDetail;
import inventory.system.entity.ReturDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReturDetailRepository extends CrudRepository<ReturDetail, Integer> {
    @Query("select a from ReturDetail a where a.retur_id=:retur_id")
    List<ReturDetail> findReturDetailByReturId(@Param("retur_id") String retur_id);

    @Query("select a from ReturDetail a join Retur o on a.retur_id = o.id where o.origin_id=:warehouse_id " +
            "and o.order_type='W' order by o.updated_at desc")
    List<ReturDetail> findAllByWarehouseOrigin(@Param("warehouse_id") String warehouse_id);

    @Query("select a from ReturDetail a join Retur o on a.retur_id = o.id where o.dest_id=:warehouse_id " +
            "and o.dest_type='W' order by o.updated_at desc")
    List<ReturDetail> findAllByWarehouseDest(@Param("warehouse_id") String warehouse_id);
}
