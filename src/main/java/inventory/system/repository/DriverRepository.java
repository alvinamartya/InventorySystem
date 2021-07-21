package inventory.system.repository;

import inventory.system.entity.Driver;
import inventory.system.entity.Warehouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    @Query("select a from Driver a where a.warehouse_id=:id_warehouse")
    public List<Driver> findDriverByWarehouse(@Param("id_warehouse") String id_warehouse);
}
