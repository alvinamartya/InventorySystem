package inventory.system.repository;

import inventory.system.model.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
}
