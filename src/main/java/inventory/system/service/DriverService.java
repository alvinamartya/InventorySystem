package inventory.system.service;

import inventory.system.model.Driver;
import inventory.system.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    DriverRepository driversRepository;

    public List<Driver> getAllDriver(){
        List<Driver> driversList = (List<Driver>) driversRepository.findAll();
        driversList.sort(Comparator.comparing(Driver::getStatus));

        return driversList;
    }

    public List<Driver> saveDriver(Driver driver){
        driver.setStatus("A");
        driver.setCreated_at(new Date());
        driver.setCreated_by("Admin");
        driver.setUpdated_at(new Date());
        driver.setUpdated_by("Admin");
        driversRepository.save(driver);
        return getAllDriver();
    }

    public int update(int id, Driver driver){
        Driver drivers = driversRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        drivers.setName(driver.getName());
        drivers.setVehicle_id(driver.getVehicle_id());
        drivers.setWarehouse_id(driver.getWarehouse_id());
        drivers.setPhone(driver.getPhone());
        drivers.setUpdated_at(new Date());
        drivers.setUpdated_by("Admin");
        driversRepository.save(drivers);

        return 1;
    }

    public Driver getDriverById(Integer id){
        Optional<Driver> optional = driversRepository.findById(id);
        Driver driver = null;
        if(optional.isPresent()){
            driver = optional.get();
        }else{
            throw new RuntimeException(" Driver not found for id :: " + id);
        }
        return driver;
    }

    public int delete(Driver driver){
        driver.setStatus("D");
        driver.setUpdated_at(new Date());
        driver.setUpdated_by("Admin");
        driversRepository.save(driver);

        return 1;
    }


    public int activate(int id, Driver driver){
        driver.setStatus("A");
        driver.setUpdated_at(new Date());
        driver.setUpdated_by("Admin");
        driversRepository.save(driver);

        return 1;
    }


}
