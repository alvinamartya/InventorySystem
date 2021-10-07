package inventory.system.repository;

import inventory.system.entity.ShelfDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShelfDetailRepository extends CrudRepository<ShelfDetail, Integer> {
    @Query("select a from ShelfDetail a where a.shelf_id=:id_shelf")
    List<ShelfDetail> findAllByShelf(@Param("id_shelf") String id_shelf);

    @Query("select count(a) from ShelfDetail a where a.shelf_id=:category_id and a.product_id =:product_id")
    Integer countFilledShelf(@Param("category_id") String category_id, @Param("product_id") Integer product_id);

    @Modifying
    @Query("delete from ShelfDetail a where a.shelf_id=:id_shelf")
    void deleteDetail(@Param("id_shelf") String id_shelf);

    @Query("select a from ShelfDetail a where a.product_id=:product_id")
    List<ShelfDetail> findAllByShelfProductId(@Param("product_id") Integer product_id);
}
