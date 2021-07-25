package inventory.system.service;

import inventory.system.entity.LoggedUser;
import inventory.system.entity.Product;
import inventory.system.entity.Supplier;
import inventory.system.entity.SupplierDetail;
import inventory.system.model.OrderDetailInputModel;
import inventory.system.repository.ProductRepository;
import inventory.system.repository.SupplierDetailsRepository;
import inventory.system.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierDetailsRepository supplierdetailRepository;

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
        List<SupplierDetail> supplierdetailList = supplierdetailRepository.findSupplierDetailBySupId(id);
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

    public int update(int id, Product product, LoggedUser loggedUser) {
        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));

        p.setName(product.getName());
        p.setProduct_category_id(product.getProduct_category_id());
        p.setUnits(product.getUnits());
        p.setUpdated_at(new Date());
        p.setUpdated_by(loggedUser.getName());

        productRepository.save(p);
        return 1;
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

    public int delete(Product product, LoggedUser loggedUser) {
        product.setStatus("D");
        product.setUpdated_at(new Date());
        product.setUpdated_by(loggedUser.getName());
        productRepository.save(product);

        return 1;
    }

    public int activate(Product product, LoggedUser loggedUser) {
        product.setStatus("A");
        product.setUpdated_at(new Date());
        product.setUpdated_by(loggedUser.getName());
        productRepository.save(product);

        return 1;
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
