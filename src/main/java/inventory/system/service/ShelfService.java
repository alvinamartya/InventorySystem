package inventory.system.service;

import inventory.system.entity.Shelf;
import inventory.system.repository.ShelfRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    ShelfRepository shelfsRepository;

    public List<Shelf> getAllShelf() {
        List<Shelf> shelfsList = (List<Shelf>) shelfsRepository.findAll();
        //shelfsList.sort(Comparator.comparing(Shelf::getStatus));

        return shelfsList;
    }

    public List<Shelf> saveShelf(Shelf shelf) {
        shelf.setId(generateId(shelf.getWarehouse_id(), shelf.getProduct_category_id(), shelf.getType()));
        shelf.setCreated_at(new Date());
        shelf.setCreated_by("Admin");
        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by("Admin");
        shelfsRepository.save(shelf);
        return getAllShelf();
    }

    public int update(String id, Shelf shelf) {
        Shelf shelfs = shelfsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shelf Id:" + id));
        shelfs.setUpdated_at(new Date());
        shelfs.setUpdated_by("Admin");
        shelfsRepository.save(shelfs);

        return 1;
    }

    public Shelf getShelfById(String id) {
        Optional<Shelf> optional = shelfsRepository.findById(id);
        Shelf shelf = null;
        if (optional.isPresent()) {
            shelf = optional.get();
        } else {
            throw new RuntimeException(" Shelf not found for id :: " + id);
        }

        return shelf;
    }

    private String generateId(String warehouseId, String productCategoryId, String warehouseType) {
        int lastCounter = getLastCounter(warehouseId, productCategoryId, warehouseType);

        String typeId = warehouseType.equals("Rak Order") ? "RO" : "RR";
        return typeId + "-" + warehouseId + "-" + productCategoryId + "-" + GeneratorId.generateMasterId(lastCounter);
    }

    private int getLastCounter(String warehouseId, String productCategoryId, String warehouseType) {
        List<Shelf> shelfList = getAllShelf();

        if (shelfList.size() > 0) {
            List<Shelf> shelfFiltered = shelfList
                    .stream()
                    .filter(x ->
                            x.getWarehouse_id().equals(warehouseId) &&
                                    x.getProduct_category_id().equals(productCategoryId) &&
                                    x.getType().equals(warehouseType))
                    .toList();

            if (shelfFiltered.size() > 0) {
                shelfFiltered.sort(Comparator.comparing(Shelf::getId));

                String[] shelfIdArr = shelfFiltered.get(shelfFiltered.size() - 1).getId().split("-");
                return Integer.parseInt(shelfIdArr[shelfIdArr.length - 1]);
            } else {
                return 0;
            }
        }

        return 0;
    }


    public int delete(Shelf shelf) {
        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by("Admin");
        shelfsRepository.save(shelf);

        return 1;
    }

}
