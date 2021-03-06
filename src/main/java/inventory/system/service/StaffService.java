package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Staffs;
import inventory.system.repository.StaffsRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Resource
    StaffsRepository staffsRepository;

    @Resource
    JavaMailSender mailSender;

    public void sendEmailStaff(Staffs staff) {
        new Thread(() -> {
            try {
                String from = "djadjan@use-narwhal.com";
                String to = staff.getEmail();
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setSubject("*WELCOME TO DJADJAN WAREHOUSE SYSTEM*");
                helper.setFrom(from);
                helper.setTo(to);

                helper.setText("" +
                        "<p>Congratulations, you are officially registered as Djadjan Warehouse Staff!</p>" +
                        "<br>" +
                        "<p>This is your account detail that can be used to access the Djadjan Warehouse Web Application</p>" +
                        "<p>staff name : " + staff.getName() + "</p>" +
                        "<p>username : <b>" + staff.getEmail() + "</b></p>" +
                        "<p>password : <b>" + "djadjanwarehouse" + "</b></p>" +
                        "<br>" +
                        "<p><b>**important!</b> your password is auto-generated by system " +
                        "please change your password immediately to secure your account by clicking this link " +
                        "<a href='http://localhost:8080/staff/update-password/" + staff.getId() + "'>CLICK HERE!</a><p>" +
                        "", true);

                mailSender.send(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
    public void sendEmailUpdatePassword(Staffs staff) {
        new Thread(() -> {
            String from = "djadjan@use-narwhal.com";
            String to = staff.getEmail();

            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setSubject("*DJADJAN PASSWORD CHANGE REQUEST*");
                helper.setFrom(from);
                helper.setTo(to);

                helper.setText("" +
                        "<p>Hello there!, you requested password change</p>" +
                        "<p>You can change your password by clicking this link " +
                        "<a href='http://localhost:8080/staff/update-password/" + staff.getId() + "'>CLICK HERE!</a><p>" +
                        "", true);

                mailSender.send(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
    public boolean isEmailExist(String email) {
        Optional<Staffs> optional = staffsRepository.findByEmail(email);
        return !optional.isPresent();
    }
    public List<Staffs> getAllStaff() {
        return staffsRepository.findAllByRole();
    }
    public void saveStaff(Staffs staffs, LoggedUser loggedUser) {
        staffs.setPassword(encodePassword("djadjanwarehouse"));
        staffs.setStatus("A");
        staffs.setCreated_at(new Date());
        staffs.setCreated_by(loggedUser.getName());
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by(loggedUser.getName());
        staffsRepository.save(staffs);
        sendEmailStaff(staffs);
    }
    public String encodePassword(String inputPassword) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(inputPassword);
    }
    public Staffs getStaffById(Integer id) {
        Optional<Staffs> optional = staffsRepository.findById(id);
        Staffs staffs = null;
        if (optional.isPresent()) {
            staffs = optional.get();
        } else {
            throw new RuntimeException(" Staff not found for id :: " + id);
        }
        return staffs;
    }
    public void update(int id, Staffs staff, LoggedUser loggedUser) {
        Staffs staffs = staffsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        staffs.setName(staff.getName());
        staffs.setPhone(staff.getPhone());
        staffs.setAddress(staff.getAddress());
        staffs.setGender(staff.getGender());
        staffs.setRole_id(staff.getRole_id());
        staffs.setWarehouse_id(staff.getWarehouse_id());

        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by(loggedUser.getName());

        staffsRepository.save(staffs);
    }
    public void updatePassword(int id, Staffs staff) {
        Staffs staffs = staffsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));

        staffs.setPassword(encodePassword(staff.getPassword()));
        staffs.setUpdated_at(new Date());

        staffsRepository.save(staffs);
    }
    public void deleteStaff(Staffs staffs, LoggedUser loggedUser) {
        staffs.setStatus("D");
        staffs.setUpdated_at(new Date());
        staffs.setUpdated_by(loggedUser.getName());
        staffsRepository.save(staffs);
    }
}
