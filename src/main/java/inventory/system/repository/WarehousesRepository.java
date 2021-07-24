package inventory.system.repository;

import inventory.system.entity.Warehouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehousesRepository extends CrudRepository<Warehouses, String> {
    @Query("select a from Warehouses a where a.is_branch=0")
    List<Warehouses> findAllPusat();

    @Query("select a from Warehouses a where a.is_branch=1")
    List<Warehouses> findAllCabang();

    @Query("select a from Warehouses a where a.is_branch=1 AND a.province=:province  AND a.status='A' ")
    List<Warehouses> findCabangByPusat(@Param("province") String province);


}
