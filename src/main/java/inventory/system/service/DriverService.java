package inventory.system.service;

import inventory.system.entity.Driver;
import inventory.system.entity.LoggedUser;
import inventory.system.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Resource
    DriverRepository driversRepository;

    public List<Driver> getAllDriver() {
        List<Driver> driversList = (List<Driver>) driversRepository.findAll();
        driversList.sort(
                Comparator
                        .comparing(Driver::getStatus)
                        .thenComparing(Driver::getName)
        );


        return driversList;
    }
    public List<Driver> getDriverByWarehouse(String id) {
        return driversRepository.findDriverByWarehouse(id);
    }
    public void saveDriver(Driver driver, LoggedUser loggedUser) {
        driver.setStatus("A");
        driver.setCreated_at(new Date());
        driver.setCreated_by(loggedUser.getName());
        driver.setUpdated_at(new Date());
        driver.setUpdated_by(loggedUser.getName());
        driversRepository.save(driver);
    }
    public void update(int id, Driver driver, LoggedUser loggedUser) {
        Driver drivers = driversRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        drivers.setName(driver.getName());
        drivers.setVehicle_id(driver.getVehicle_id());
        drivers.setWarehouse_id(driver.getWarehouse_id());
        drivers.setPhone(driver.getPhone());
        drivers.setUpdated_at(new Date());
        drivers.setUpdated_by(loggedUser.getName());
        driversRepository.save(drivers);
    }
    public Driver getDriverById(Integer id) {
        Optional<Driver> optional = driversRepository.findById(id);
        Driver driver = null;
        if (optional.isPresent()) {
            driver = optional.get();
        } else {
            throw new RuntimeException(" Driver not found for id :: " + id);
        }
        return driver;
    }
    public void delete(Driver driver, LoggedUser loggedUser) {
        driver.setStatus("D");
        driver.setUpdated_at(new Date());
        driver.setUpdated_by(loggedUser.getName());
        driversRepository.save(driver);
    }
    public void activate(Driver driver, LoggedUser loggedUser) {
        driver.setStatus("A");
        driver.setUpdated_at(new Date());
        driver.setUpdated_by(loggedUser.getName());
        driversRepository.save(driver);
    }
}
