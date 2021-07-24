package inventory.system.repository;

import inventory.system.entity.Driver;
import inventory.system.entity.Staffs;
import inventory.system.entity.Stores;
import inventory.system.entity.Warehouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.List;

public interface WarehousesRepository extends CrudRepository<Warehouses, String> {
    @Query("select a from Warehouses a where a.id=:id")
    public Staffs findWarehousesById(@Param("id") String id);

    @Query("select a from Warehouses a where a.is_branch=0")
    public List<Warehouses> findAllPusat();

    @Query("select a from Warehouses a where a.is_branch=1")
    public List<Warehouses> findAllCabang();

    @Query("select a from Warehouses a where a.is_branch=1 AND a.province=:province  AND a.status='A' ")
    public List<Warehouses> findCabangByPusat(@Param("province") String province);

    @Query("select a from Warehouses a where a.is_branch=0 AND a.id=:id  AND a.status='A' ")
    public List<Warehouses> findPusatById(@Param("id") String id);

    @Query("select a from Warehouses a where a.is_branch=0 AND a.province=:province  AND a.status='A' ")
    public List<Warehouses> findPusatByCabang(@Param("province") String province);

    @Query("select a from Stores a where a.city=:city AND a.status='A' ")
    public List<Stores> findStoreByCabang(@Param("city") String city);

    @Query("select a from Warehouses a where a.is_branch=1 AND a.city=:city  AND a.status='A' ")
    public List<Warehouses> findCabangByStore(@Param("city") String city);

    @Query("select a from Driver a where a.warehouse_id=:warehouse_id AND a.status='A' ")
    public List<Driver> findDriverByCabang(@Param("warehouse_id") String warehouse_id);

}
