package inventory.system.repository;

import inventory.system.entity.Shelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, String> {
    @Query("select a from Shelf a where a.type_shelf='Rak Order'")
    List<Shelf> findAllRO();
    @Query("select a from Shelf a where a.type_shelf='Rak Retur'")
    List<Shelf> findAllRR();

    @Query("select a from Shelf a where a.product_category_id=:category_id and a.type_shelf='Rak Order'")
    List<Shelf> findShelfByCategoryRO(@Param("category_id") String category_id);

    @Query("select a from Shelf a where a.product_category_id=:category_id and a.type_shelf='Rak Retur'")
    List<Shelf> findShelfByCategoryRR(@Param("category_id") String category_id);
}
