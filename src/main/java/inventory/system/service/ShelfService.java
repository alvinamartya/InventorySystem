package inventory.system.service;

import inventory.system.entity.Shelf;
import inventory.system.entity.ShelfDetail;
import inventory.system.repository.ShelfDetailRepository;
import inventory.system.repository.ShelfRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShelfService {

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ShelfDetailRepository shelfDetailRepository;

    public List<Shelf> getAllShelf() {
        return (List<Shelf>) shelfRepository.findAll();
    }

    public List<Shelf> getAllShelfRO(){
        return shelfRepository.findAllRO();
    }
    public List<Shelf> getAllShelfRR(){
        return shelfRepository.findAllRR();
    }

    public void saveShelf(Shelf shelf) {
        Shelf newShelf = new Shelf();
        String shelfId = generateId(shelf.getWarehouse_id(), shelf.getProduct_category_id(), shelf.getType_shelf());
        int row = shelf.getRows_shelf();
        int col = shelf.getColumns_shelf();
        int quantity = shelf.getQuantity_shelf();
        newShelf.setId(shelfId);
        newShelf.setWarehouse_id(shelf.getWarehouse_id());
        newShelf.setType_shelf(shelf.getType_shelf());
        newShelf.setProduct_category_id(shelf.getProduct_category_id());
        newShelf.setRows_shelf(row);
        newShelf.setColumns_shelf(col);
        newShelf.setQuantity_shelf(quantity);
        newShelf.setCreated_at(new Date());
        newShelf.setCreated_by("Admin");
        newShelf.setUpdated_at(new Date());
        newShelf.setUpdated_by("Admin");
        newShelf.setIs_empty(1);

        shelfRepository.save(newShelf);
        insertDetail(shelfId, row, col, quantity);

        getAllShelf();
    }

    public void insertDetail(String shelfId, int row, int col, int quantity){
        List<ShelfDetail> arrayShelfDetail = new ArrayList<ShelfDetail>();

        int rowCounter, colCounter;
        for(int i = 1; i<=row; i++){
            rowCounter = i;

            for(int j = 1; j<=col; j++){
                colCounter = j;

                for(int k = 1; k<=quantity; k++){
                    ShelfDetail shelfdetail = new ShelfDetail();
                    shelfdetail.setShelf_id(shelfId);
                    shelfdetail.setRow_shelf(rowCounter);
                    shelfdetail.setCol_shelf(colCounter);
                    arrayShelfDetail.add(shelfdetail);
                }
            }

        }

        shelfDetailRepository.saveAll(arrayShelfDetail);
    }

    public List<ShelfDetail> getShelfDetail(String id) {
        return shelfDetailRepository.findAllByShelf(id);
    }

    public void update(String id) {
        Shelf shelf = shelfRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shelf Id:" + id));

        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by("Admin");
        shelfRepository.save(shelf);
    }

    public Shelf getShelfById(String id) {
        Optional<Shelf> optional = shelfRepository.findById(id);
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
                                    x.getType_shelf().equals(warehouseType))
                    .collect(Collectors.toList());

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

    @Transactional
    public void delete(Shelf shelf) {
        shelfDetailRepository.deleteDetail(shelf.getId());
        shelfRepository.delete(shelf);
    }
}
