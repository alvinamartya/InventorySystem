package inventory.system.service;

import inventory.system.entity.Staffs;
import inventory.system.repository.StaffsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffsRepository staffsRepository;

    public boolean isEmailExist(String email){
        Optional<Staffs> optional = staffsRepository.findByEmail(email);
        return !optional.isPresent();
    }

    public List<Staffs> getAllStaff(){
        return (List<Staffs>) staffsRepository.findAll();
    }

    public void saveStaff(Staffs staffs){
        staffs.setPassword(encodePassword(staffs.getPassword()));
        staffs.setStatus("A");
        staffs.setCreated_at(new Date());
        staffs.setCreated_by("Admin");
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by("Admin");
        staffsRepository.save(staffs);
    }

    public String encodePassword(String inputPassword){
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(inputPassword);
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

    public void deleteStaff(Staffs staffs){
        staffs.setStatus("D");
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by("Admin");
        staffsRepository.save(staffs);
    }
}
