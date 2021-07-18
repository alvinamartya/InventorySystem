package inventory.system.repository;

import inventory.system.entity.Driver;
import inventory.system.entity.Shelf;
import inventory.system.entity.Warehouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, String> {
    @Query("select a from Shelf a where a.type_shelf='Rak Order'")
    public List<Shelf> findAllRO();
    @Query("select a from Shelf a where a.type_shelf='Rak Retur'")
    public List<Shelf> findAllRR();
}
