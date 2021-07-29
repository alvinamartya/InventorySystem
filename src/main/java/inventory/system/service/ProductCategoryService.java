package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.ProductCategory;
import inventory.system.repository.ProductCategoryRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Resource
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllProductCategory() {
        List<ProductCategory> productCategoryList = (List<ProductCategory>) productCategoryRepository.findAll();
        productCategoryList.sort(
                Comparator
                        .comparing(ProductCategory::getStatus)
                        .thenComparing(ProductCategory::getName)
        );
        return productCategoryList;
    }
    public void saveProductCategory(ProductCategory productCategory, LoggedUser loggedUser) {
        productCategory.setId(GeneratorId.generateMasterId(getLastCounter()));
        productCategory.setStatus("A");
        productCategory.setCreated_at(new Date());
        productCategory.setCreated_by(loggedUser.getName());
        productCategory.setUpdated_by(loggedUser.getName());
        productCategory.setUpdated_at(new Date());
        productCategoryRepository.save(productCategory);
    }
    private int getLastCounter() {
        List<ProductCategory> productCategoryList = getAllProductCategory();
        if (productCategoryList.size() > 0) {
            productCategoryList.sort(Comparator.comparing(ProductCategory::getId));
            return Integer.parseInt(productCategoryList.get(productCategoryList.size() - 1).getId());
        }

        return 0;
    }
    public void update(String id, ProductCategory productCategory) {
        ProductCategory p = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product category Id: " + id));

        p.setName(productCategory.getName());
        p.setIs_can_be_stale(productCategory.getIs_can_be_stale());
        productCategoryRepository.save(p);
    }
    public ProductCategory getProductCategoryById(String id) {
        Optional<ProductCategory> optional = productCategoryRepository.findById(id);
        ProductCategory productCategory = null;
        if (optional.isPresent()) {
            productCategory = optional.get();
        } else {
            throw new RuntimeException(" Product Category not found for id :: " + id);
        }

        return productCategory;
    }
    public void delete(ProductCategory productCategory, LoggedUser loggedUser) {
        productCategory.setStatus("D");
        productCategory.setUpdated_at(new Date());
        productCategory.setUpdated_by(loggedUser.getName());
        productCategoryRepository.save(productCategory);
    }
    public void activate(ProductCategory productCategory, LoggedUser loggedUser) {
        productCategory.setStatus("A");
        productCategory.setUpdated_at(new Date());
        productCategory.setUpdated_by(loggedUser.getName());
        productCategoryRepository.save(productCategory);
    }
}
