package inventory.system.repository;

import inventory.system.entity.Staffs;
import inventory.system.entity.Warehouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WarehousesRepository extends CrudRepository<Warehouses, String> {
    @Query("select a from Warehouses a where a.id=:id")
    public Staffs findWarehousesById(@Param("id") String id);
}
