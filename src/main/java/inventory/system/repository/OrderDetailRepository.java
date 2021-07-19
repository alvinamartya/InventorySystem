package inventory.system.repository;

import inventory.system.entity.Order;
import inventory.system.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    @Query("select a from OrderDetail a where a.order_id=:id_order")
    public List<OrderDetail> findAllByOrder(@Param("id_order") String id_order);
}
