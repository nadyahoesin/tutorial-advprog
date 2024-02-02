package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product find(String productName) {
        for (Product product: productData) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }

        return new Product();
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void delete(String productName) {
        Product product = find(productName);
        productData.remove(product);
    }

    public void editName(String oldProductName, String newProductName) {
        Product product = find(oldProductName);
        product.setProductName(newProductName);
    }
}
