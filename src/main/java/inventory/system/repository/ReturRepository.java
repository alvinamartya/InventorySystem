package inventory.system.repository;

import inventory.system.entity.Retur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReturRepository extends CrudRepository<Retur, String> {

    @Query("select a from Retur a where a.warehouse_at=:warehouse_id " +
            "AND a.order_type='T' AND a.dest_type='W' order by a.updated_at desc")
    public List<Retur> findByWarehouseLv1(@Param("warehouse_id") String warehouse_id);

    @Query("select a from Retur a where a.warehouse_at=:warehouse_id " +
            "AND a.order_type='W' AND a.dest_type='W' order by a.updated_at desc")
    public List<Retur> findByWarehouseLv2(@Param("warehouse_id") String warehouse_id);

    @Query("select a from Retur a where a.warehouse_at=:warehouse_id " +
            "AND a.order_type='W' AND a.dest_type='S' order by a.updated_at desc")
    public List<Retur> findByWarehouseLv3(@Param("warehouse_id") String warehouse_id);
}
