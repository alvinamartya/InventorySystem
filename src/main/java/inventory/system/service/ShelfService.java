package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Product;
import inventory.system.entity.Shelf;
import inventory.system.entity.ShelfDetail;
import inventory.system.model.ShelfCustomJSONModel;
import inventory.system.repository.ProductRepository;
import inventory.system.repository.ShelfDetailRepository;
import inventory.system.repository.ShelfRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShelfService {

    @Resource
    ShelfRepository shelfRepository;

    @Resource
    ProductService productService;

    @Resource
    ShelfDetailRepository shelfDetailRepository;

    public List<Shelf> getAllShelf() {
        return (List<Shelf>) shelfRepository.findAll();
    }
    public List<Shelf> getAllShelfRO() {
        return shelfRepository.findAllRO();
    }
    public List<Shelf> getAllShelfRR() {
        return shelfRepository.findAllRR();
    }
    public List<Shelf> getShelfByCategory(String id, String warehouse_id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        return shelfRepository.findShelfByCategoryRO(product.getProduct_category_id(), warehouse_id);
    }
    public List<ShelfCustomJSONModel> getShelfByCategoryAndProduct(String id, String warehouse_id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        List<Shelf> shelfList = shelfRepository.findShelfByCategoryRO(product.getProduct_category_id(), warehouse_id);
        List<ShelfCustomJSONModel> shelfFiltered = new ArrayList<>();
        for (Shelf obj : shelfList)
        {
            List<ShelfDetail> dt = shelfDetailRepository.findAllByShelf(obj.getId());
            for (ShelfDetail objdt : dt){
                if(objdt.getProduct_id()!=null){
                    if(objdt.getProduct_id()==product.getId()){
                        ShelfCustomJSONModel objadd = new ShelfCustomJSONModel();
                        objadd.setId(obj.getId());
                        objadd.setName(obj.getId());
                        objadd.setTotalShelf(0);
                        objadd.setFilled(shelfDetailRepository.countFilledShelf(obj.getId(),product.getId()));
                        objadd.setEmptyShelf(0);
                        objadd.setCapacityText("qty:"+objadd.getFilled());
                        shelfFiltered.add(objadd);
                        break;
                    }
                }
            }
        }
        return shelfFiltered;
    }
    public Integer countFilledShelf(String id){
        return shelfRepository.countFilledShelf(id);
    }
    public List<Shelf> getShelfByCategoryRR(String id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        return shelfRepository.findShelfByCategoryRR(product.getProduct_category_id());
    }
    public List<Shelf> getAllShelfByWarehouseRO(String warehouse_id) {
        return shelfRepository.findShelfByWarehouseRO(warehouse_id);
    }
    public List<Shelf> getAllShelfByWarehouseRR(String warehouse_id) {
        return shelfRepository.findShelfByWarehouseRR(warehouse_id);
    }
    public List<ShelfDetail> getAllShelfById(String shelf_id) {
        return shelfRepository.findShelfDetailById(shelf_id);
    }
    public void saveShelf(Shelf shelf, LoggedUser loggedUser) {
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
        newShelf.setCreated_by(loggedUser.getName());
        newShelf.setUpdated_at(new Date());
        newShelf.setUpdated_by(loggedUser.getName());
        newShelf.setIs_empty(1);

        shelfRepository.save(newShelf);
        insertDetail(shelfId, row, col, quantity);

        getAllShelf();
    }
    public void insertDetail(String shelfId, int row, int col, int quantity) {
        List<ShelfDetail> arrayShelfDetail = new ArrayList<ShelfDetail>();

        int rowCounter, colCounter;
        for (int i = 1; i <= row; i++) {
            rowCounter = i;

            for (int j = 1; j <= col; j++) {
                colCounter = j;

                for (int k = 1; k <= quantity; k++) {
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
    public void update(String id, LoggedUser loggedUser) {
        Shelf shelf = shelfRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shelf Id:" + id));

        shelf.setUpdated_at(new Date());
        shelf.setUpdated_by(loggedUser.getName());
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
