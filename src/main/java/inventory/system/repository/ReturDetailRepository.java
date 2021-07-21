package inventory.system.repository;

import inventory.system.entity.ReturDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReturDetailRepository extends CrudRepository<ReturDetail, Integer> {
    @Query("select a from ReturDetail a where a.retur_id=:retur_id")
    List<ReturDetail> findReturDetailByReturId(@Param("retur_id") String retur_id);
}
