package inventory.system.repository;

import inventory.system.model.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends CrudRepository<Shelf, String> {
}
