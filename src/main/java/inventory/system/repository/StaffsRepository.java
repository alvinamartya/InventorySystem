package inventory.system.repository;

import inventory.system.entity.ShelfDetail;
import inventory.system.entity.Staffs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffsRepository extends CrudRepository<Staffs, Integer> {
    @Query("select a from Staffs a where a.phone=:phone")
    public Staffs findStaffByPhone(@Param("phone") String phone);

    @Query("select a from Staffs a where a.email=:email")
    public Optional<Staffs> findByEmail(@Param("email") String email);
}
