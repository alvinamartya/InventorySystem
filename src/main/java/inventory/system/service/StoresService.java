package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Stores;
import inventory.system.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoresService {

    @Resource
    StoresRepository storesRepository;

    public List<Stores> getAllStores() {
        List<Stores> storesList = storesRepository.findAll();
        storesList.sort(
                Comparator
                        .comparing(Stores::getStatus)
                        .thenComparing(Stores::getName)
        );
        return storesList;
    }
    public List<Stores> saveStores(Stores stores, LoggedUser loggedUser) {
        stores.setStatus("A");
        stores.setCreated_at(new Date());
        stores.setCreated_by(loggedUser.getName());
        stores.setUpdated_at(new Date());
        stores.setUpdated_by(loggedUser.getName());
        storesRepository.save(stores);
        return getAllStores();
    }
    public void update(int id, Stores store, LoggedUser loggedUser) {
        Stores stores = storesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));
        stores.setName(store.getName());
        stores.setCity(store.getCity());
        stores.setProvince(store.getProvince());
        stores.setAddress(store.getAddress());
        stores.setHead_of_store_name(store.getHead_of_store_name());
        stores.setPhone(store.getPhone());
        stores.setUpdated_at(new Date());
        stores.setUpdated_by(loggedUser.getName());
        storesRepository.save(stores);
    }
    public Stores getStoresById(Integer id) {
        Optional<Stores> optional = storesRepository.findById(id);
        Stores stores = null;
        if (optional.isPresent()) {
            stores = optional.get();
        } else {
            throw new RuntimeException(" stores not found for id :: " + id);
        }
        return stores;
    }
    public List<Stores> deleteStores(Stores stores, LoggedUser loggedUser) {
        stores.setStatus("D");
        stores.setUpdated_at(new Date());
        stores.setUpdated_by(loggedUser.getName());
        storesRepository.save(stores);
        return getAllStores();
    }
    public void activate(Stores stores, LoggedUser loggedUser) {
        stores.setStatus("A");
        stores.setUpdated_at(new Date());
        stores.setUpdated_by(loggedUser.getName());
        storesRepository.save(stores);
    }
}
