package inventory.system.repository;

import inventory.system.entity.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    @Query("select a from Driver a where a.warehouse_id=:id_warehouse")
    List<Driver> findDriverByWarehouse(@Param("id_warehouse") String id_warehouse);

    @Query("select a from Driver a where a.warehouse_id=:id_warehouse and a.is_available=1")
    List<Driver> findDriverByWarehouseAvailable(@Param("id_warehouse") String id_warehouse);
}
