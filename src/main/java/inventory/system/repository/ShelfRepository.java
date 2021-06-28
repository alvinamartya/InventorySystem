package inventory.system.repository;

import inventory.system.entity.Driver;
import inventory.system.entity.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends CrudRepository<Shelf, String> {

}
