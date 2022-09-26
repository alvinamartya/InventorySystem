package inventory.system.repository;

import inventory.system.entity.Shelf;
import inventory.system.entity.ShelfDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, String> {
    @Query("select a from Shelf a where a.type_shelf='Rak Order'")
    List<Shelf> findAllRO();
    @Query("select a from Shelf a where a.type_shelf='Rak Retur'")
    List<Shelf> findAllRR();

    @Query("select a from Shelf a where a.product_category_id=:category_id and a.type_shelf='Rak Order' and a.warehouse_id=:warehouse_id")
    List<Shelf> findShelfByCategoryRO(@Param("category_id") String category_id, @Param("warehouse_id") String warehouse_id);


    @Query("select count(a) from ShelfDetail a where a.shelf_id=:category_id and a.product_id is not null")
    Integer countFilledShelf(@Param("category_id") String category_id);

    @Query("select a from Shelf a where a.product_category_id=:category_id and a.type_shelf='Rak Retur'")
    List<Shelf> findShelfByCategoryRR(@Param("category_id") String category_id);
  
    @Query("select a from ShelfDetail a where a.shelf_id=:shelf_id")
    List<ShelfDetail> findShelfDetailById(@Param("shelf_id") String shelf_id);

    @Query("select a from Shelf a where a.warehouse_id=:warehouse_id and a.type_shelf='Rak Order'")
    List<Shelf> findShelfByWarehouseRO(@Param("warehouse_id") String warehouse_id);

    @Query("select a from Shelf a where a.warehouse_id=:warehouse_id and a.type_shelf='Rak Retur'")
    List<Shelf> findShelfByWarehouseRR(@Param("warehouse_id") String warehouse_id);
}
