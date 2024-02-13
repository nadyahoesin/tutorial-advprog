package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public void delete(String productName) throws Exception;
    public Product find(String productName);
    public void editName(String productName, String newProductName) throws Exception;
    public void editQuantity(String productName, int newProductQuantity) throws Exception;
}
