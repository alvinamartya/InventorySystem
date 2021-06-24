package inventory.system.service;

import inventory.system.entity.Shelf;
import inventory.system.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    ShelfRepository shelfsRepository;

    public List<Shelf> getAllShelf(){
        List<Shelf> shelfsList = (List<Shelf>) shelfsRepository.findAll();
        //shelfsList.sort(Comparator.comparing(Shelf::getStatus));

        return shelfsList;
    }

    public List<Shelf> saveShelf(Shelf shelf){
        shelf.setCreated_at(new Date());
        shelf.setCreated_by("Admin");
        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by("Admin");
        shelfsRepository.save(shelf);
        return getAllShelf();
    }

    public int update(String id, Shelf shelf){
        Shelf shelfs = shelfsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shelf Id:" + id));
        shelfs.setUpdated_at(new Date());
        shelfs.setUpdated_by("Admin");
        shelfsRepository.save(shelfs);

        return 1;
    }

    public Shelf getShelfById(String id){
        Optional<Shelf> optional = shelfsRepository.findById(id);
        Shelf shelf = null;
        if(optional.isPresent()){
            shelf = optional.get();
        }else{
            throw new RuntimeException(" Shelf not found for id :: " + id);
        }

        return shelf;
    }

    public int delete(Shelf shelf){
        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by("Admin");
        shelfsRepository.save(shelf);

        return 1;
    }

}
