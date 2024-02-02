package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public void delete(String productName) {
        productRepository.delete(productName);
    }

    @Override
    public Product find(String productName) {
        return productRepository.find(productName);
    }

    @Override
    public void editName(String productName, String newProductName) {
        productRepository.editName(productName, newProductName);
    }

    @Override
    public void editQuantity(String productName, int newProductQuantity) {
        productRepository.editQuantity(productName, newProductQuantity);
    }
}