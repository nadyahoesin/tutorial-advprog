package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public void delete(String productName);
    public Product find(String productName);
    public void editName(String productName, String newProductName);
    public void editQuantity(String productName, int newProductQuantity);
}
