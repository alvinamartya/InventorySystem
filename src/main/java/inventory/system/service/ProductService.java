package inventory.system.service;

import inventory.system.entity.Product;
import inventory.system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        productList.sort(
                Comparator
                        .comparing(Product::getStatus)
                        .thenComparing(Product::getName)
        );

        return productList;
    }

    public void saveProduct(Product product) {
        product.setStatus("A");
        product.setCreated_at(new Date());
        product.setCreated_by("Admin");
        product.setUpdated_at(new Date());
        product.setUpdated_by("Admin");
        productRepository.save(product);
    }

    public int update(int id, Product product) {
        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));

        p.setName(product.getName());
        p.setProduct_category_id(product.getProduct_category_id());
        p.setUnits(product.getUnits());
        p.setUpdated_at(new Date());
        p.setUpdated_by("Admin");

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

    public int delete(Product product) {
        product.setStatus("D");
        product.setUpdated_at(new Date());
        product.setUpdated_by("Admin");
        productRepository.save(product);

        return 1;
    }

    public int activate(Product product) {
        product.setStatus("A");
        product.setUpdated_at(new Date());
        product.setUpdated_by("Admin");
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
