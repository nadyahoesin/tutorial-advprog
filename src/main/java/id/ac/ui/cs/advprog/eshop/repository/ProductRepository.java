package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private static final String NOT_EXIST_EXCEPTION_MESSAGE = "Product doesn't exist";

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

        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void delete(String productName) throws NoSuchElementException {
        Product product = find(productName);
        if (product == null) {
            throw new NoSuchElementException(NOT_EXIST_EXCEPTION_MESSAGE);
        }

        productData.remove(product);
    }

    public void editName(String productName, String newProductName) throws NoSuchElementException {
        Product product = find(productName);
        if (product == null) {
            throw new NoSuchElementException(NOT_EXIST_EXCEPTION_MESSAGE);
        }

        product.setProductName(newProductName);
    }

    public void editQuantity(String productName, int newProductQuantity) throws NoSuchElementException {
        Product product = find(productName);
        if (product == null) {
            throw new NoSuchElementException(NOT_EXIST_EXCEPTION_MESSAGE);
        }

        product.setProductQuantity(newProductQuantity);
    }
}
