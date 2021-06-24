package inventory.system.repository;

import inventory.system.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, String> {
}
