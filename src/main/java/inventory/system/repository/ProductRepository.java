package inventory.system.repository;

import inventory.system.entity.Product;
import inventory.system.entity.SupplierDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("select a from Product a where a.product_category_id=:category_id")
    List<Product> findBySupplierId(@Param("category_id") String category_id);
}
