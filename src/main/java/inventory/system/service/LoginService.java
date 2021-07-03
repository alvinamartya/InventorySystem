package inventory.system.service;

import inventory.system.entity.Driver;
import inventory.system.entity.Staffs;
import inventory.system.repository.DriverRepository;
import inventory.system.repository.StaffsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    StaffsRepository staffsRepository;

    public boolean checkEmail(String email){
        Optional<Staffs> optional = staffsRepository.findByEmail(email);
        Staffs staff = null;
        if (optional.isPresent()) {
            staff = optional.get();
        } else {
            return false;
        }

        return true;
    }

    public Staffs getStaff(String email){
        Optional<Staffs> optional = staffsRepository.findByEmail(email);
        Staffs staff = null;
        if (optional.isPresent()) {
            staff = optional.get();
        }

        return staff;
    }

    public boolean authentication(String password,String passwordInput){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(passwordInput, password);
    }

}
