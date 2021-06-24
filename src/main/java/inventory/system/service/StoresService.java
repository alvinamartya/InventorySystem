package inventory.system.service;

import inventory.system.model.Stores;
import inventory.system.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoresService {

    @Autowired
    StoresRepository storesRepository;

    public List<Stores> getAllStores(){
        List<Stores> storesList = (List<Stores>) storesRepository.findAll();
        return storesList;
    }

    public List<Stores> saveStores(Stores stores){
        stores.setStatus("A");
        stores.setCreated_at(new Date());
        stores.setCreated_by("Admin");
        stores.setUpdated_at(new Date());
        stores.setUpdated_by("Admin");
        storesRepository.save(stores);
        return getAllStores();
    }

    public int update(int id, Stores store){
        Stores stores = storesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));
        stores.setName(store.getName());
        stores.setCity(store.getCity());
        stores.setProvince(store.getProvince());
        stores.setAddress(store.getAddress());
        stores.setHead_of_store_name(store.getHead_of_store_name());
        stores.setPhone(store.getPhone());
        stores.setUpdated_at(new Date());
        stores.setUpdated_by("Admin");
        storesRepository.save(stores);

        return 1;
    }

    public Stores getStoresById(Integer id){
        Optional<Stores> optional = storesRepository.findById(id);
        Stores stores = null;
        if(optional.isPresent()){
            stores = optional.get();
        }else{
            throw new RuntimeException(" stores not found for id :: " + id);
        }
        return stores;
    }

    public List<Stores> deleteStores(Stores stores){
        stores.setStatus("D");
        stores.setUpdated_at(new Date());
        stores.setUpdated_by("Admin");
        storesRepository.save(stores);
        return getAllStores();
    }
}
