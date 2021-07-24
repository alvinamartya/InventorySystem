package inventory.system.repository;

import inventory.system.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoresRepository extends JpaRepository<Stores, Integer> {
    @Query("select a from Stores a where a.city=:city AND a.status='A' ")
    List<Stores> findStoreByCabang(@Param("city") String city);
}
