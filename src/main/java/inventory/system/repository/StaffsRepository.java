package inventory.system.repository;

import inventory.system.entity.Staffs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffsRepository extends CrudRepository<Staffs, Integer> {

    @Query("select a from Staffs a where a.email=:email")
    Optional<Staffs> findByEmail(@Param("email") String email);

    @Query("select a from Staffs a where a.role_id <> 4")
    List<Staffs> findAllByRole();
}
