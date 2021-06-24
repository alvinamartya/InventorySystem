package inventory.system.service;

import inventory.system.entity.Staffs;
import inventory.system.repository.StaffsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffsRepository staffsRepository;

    public List<Staffs> getAllStaff(){
        List<Staffs> staffsList = (List<Staffs>) staffsRepository.findAll();
        return staffsList;
    }

    public List<Staffs> saveStaff(Staffs staffs){
        staffs.setStatus("A");
        staffs.setCreated_at(new Date());
        staffs.setCreated_by("Admin");
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by("Admin");
        staffsRepository.save(staffs);
        return getAllStaff();
    }

    public Staffs getStaffById(Integer id){
        Optional<Staffs> optional = staffsRepository.findById(id);
        Staffs staffs = null;
        if(optional.isPresent()){
            staffs = optional.get();
        }else{
            throw new RuntimeException(" Staff not found for id :: " + id);
        }
        return staffs;
    }

    public List<Staffs> deleteStaff(Staffs staffs){
        staffs.setStatus("D");
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by("Admin");
        staffsRepository.save(staffs);
        return getAllStaff();
    }


}
