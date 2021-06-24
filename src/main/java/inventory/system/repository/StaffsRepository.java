package inventory.system.repository;

import inventory.system.model.Staffs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StaffsRepository extends CrudRepository<Staffs, Integer> {
    @Query("select a from Staffs a where a.phone=:phone")
    public Staffs findStaffByPhone(@Param("phone") String phone);
}
