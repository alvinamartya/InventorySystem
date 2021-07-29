package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Product;
import inventory.system.entity.SupplierDetail;
import inventory.system.repository.ProductRepository;
import inventory.system.repository.SupplierDetailsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class ProductService {

    @Resource
    ProductRepository productRepository;

    @Resource
    SupplierDetailsRepository supplierDetailsRepository;

    public List<Product> getAllProduct() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        productList.sort(
                Comparator
                        .comparing(Product::getStatus)
                        .thenComparing(Product::getName)
        );

        return productList;
    }
    public List<Product> getProductBySupplier(String id) {
        List<SupplierDetail> supplierdetailList = supplierDetailsRepository.findSupplierDetailBySupId(id);
        List<Product> productList = new ArrayList<Product>();
        for (SupplierDetail suppDetail : supplierdetailList) {
            productList.addAll(productRepository.findBySupplierId(suppDetail.getProduct_category_id()));
        }
        return productList;
    }
    public void saveProduct(Product product, LoggedUser loggedUser) {
        product.setStatus("A");
        product.setCreated_at(new Date());
        product.setCreated_by(loggedUser.getName());
        product.setUpdated_at(new Date());
        product.setUpdated_by(loggedUser.getName());
        productRepository.save(product);
    }
    public void update(int id, Product product, LoggedUser loggedUser) {
        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));

        p.setName(product.getName());
        p.setProduct_category_id(product.getProduct_category_id());
        p.setUnits(product.getUnits());
        p.setUpdated_at(new Date());
        p.setUpdated_by(loggedUser.getName());

        productRepository.save(p);
    }
    public Product getProductById(int id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + id);
        }

        return product;
    }
    public void delete(Product product, LoggedUser loggedUser) {
        product.setStatus("D");
        product.setUpdated_at(new Date());
        product.setUpdated_by(loggedUser.getName());
        productRepository.save(product);
    }
    public void activate(Product product, LoggedUser loggedUser) {
        product.setStatus("A");
        product.setUpdated_at(new Date());
        product.setUpdated_by(loggedUser.getName());
        productRepository.save(product);
    }
    public List<String> getUnits() {
        List<String> unit = new ArrayList<>();

        unit.add("Pcs");
        unit.add("Unit");
        unit.add("Kg");
        unit.add("Grm");
        unit.add("Botol");
        unit.add("Meter");
        unit.add("Dus");
        return unit;
    }
}
