package inventory.system.service;

import inventory.system.entity.Shelf;
import inventory.system.entity.ShelfDetail;
import inventory.system.entity.Warehouses;
import inventory.system.repository.ShelfDetailRepository;
import inventory.system.repository.ShelfRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShelfService {

    @Autowired
    ShelfRepository shelfsRepository;

    @Autowired
    ShelfDetailRepository shelfdetailsRepository;

    public List<Shelf> getAllShelf() {
        List<Shelf> shelfsList = (List<Shelf>) shelfsRepository.findAll();
//        shelfsList.sort(
//                Comparator
//                        .comparing(Shelf::getStatus)
//                        .thenComparing(Shelf::getName)
//        );

        return shelfsList;
    }

    public List<Shelf> getAllShelfRO(){
        List<Shelf> shelfList = shelfsRepository.findAllRO();

        return shelfList;
    }
    public List<Shelf> getAllShelfRR(){
        List<Shelf> shelfList = shelfsRepository.findAllRR();

        return shelfList;
    }

//    public List<Shelf> saveShelf(Shelf shelf) {
//        shelf.setCreated_at(new Date());
//        shelf.setCreated_by("Admin");
//        shelf.setUpdated_at(new Date());
//        shelf.setUpdated_by("Admin");
//        shelfsRepository.save(shelf);
//        return getAllShelf();
//    }

    public List<Shelf> saveShelf(Shelf shelf) {
        Shelf shelfs = new Shelf();
        String shelfid = generateId(shelf.getWarehouse_id(), shelf.getProduct_category_id(), shelf.getType_shelf());
        int row = shelf.getRows_shelf();
        int col = shelf.getColumns_shelf();
        int quantity = shelf.getQuantity_shelf();
        shelfs.setId(shelfid);
        shelfs.setWarehouse_id(shelf.getWarehouse_id());
        shelfs.setType_shelf(shelf.getType_shelf());
        shelfs.setProduct_category_id(shelf.getProduct_category_id());
        shelfs.setRows_shelf(row);
        shelfs.setColumns_shelf(col);
        shelfs.setQuantity_shelf(quantity);
        shelfs.setCreated_at(new Date());
        shelfs.setCreated_by("Admin");
        shelfs.setUpdated_at(new Date());
        shelfs.setUpdated_by("Admin");
        shelfs.setIs_empty(1);

        shelfsRepository.save(shelfs);
        insertDetail(shelfid, row, col, quantity);

        return getAllShelf();
    }

    public int insertDetail(String shelfid, int row, int col, int quantity){
        List<ShelfDetail> arrayShelfDetail = new ArrayList<ShelfDetail>();

        int rowCounter, colCounter;
        for(int i = 1; i<=row; i++){
            rowCounter = i;

            for(int j = 1; j<=col; j++){
                colCounter = j;

                for(int k = 1; k<=quantity; k++){
                    ShelfDetail shelfdetail = new ShelfDetail();
                    shelfdetail.setShelf_id(shelfid);
                    shelfdetail.setRow_shelf(rowCounter);
                    shelfdetail.setCol_shelf(colCounter);
                    arrayShelfDetail.add(shelfdetail);
                }
            }

        }
        shelfdetailsRepository.saveAll(arrayShelfDetail);

        return 1;
    }

    public List<ShelfDetail> getShelfDetail(String id) {
        List<ShelfDetail> listDetail = shelfdetailsRepository.findAllByShelf(id);
        return listDetail;
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
        return typeId + "-" + seperateID(warehouseId, "WR") + "-" + seperateID(productCategoryId,"PC") + "-" + GeneratorId.generateMasterId(lastCounter);
    }

    private String seperateID(String id, String target) {
        int seperatedID = Integer.parseInt(id.replace(target, "").replace("0", ""));
        String fullID="";

        if(seperatedID<10){
            fullID="0000"+seperatedID;
        }
        else if(seperatedID<100){
            fullID="000"+seperatedID;
        }
        else if(seperatedID<1000){
            fullID="00"+seperatedID;
        }
        else if(seperatedID<10000){
            fullID="0"+seperatedID;
        }
        else if(seperatedID<100000){
            fullID=""+seperatedID;
        }
        return fullID;
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
        shelfdetailsRepository.deleteDetail(shelf.getId());
        shelfsRepository.delete(shelf);
    }



}
