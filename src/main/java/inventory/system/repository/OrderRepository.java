package inventory.system.repository;

import inventory.system.entity.Order;
import inventory.system.entity.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {

}
