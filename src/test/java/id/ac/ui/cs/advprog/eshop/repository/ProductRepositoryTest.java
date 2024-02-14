package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product productFromFind = productRepository.find("Sampo Cap Bambang");
        assertEquals(product, productFromFind);
    }

    @Test
    void testFindIfEmpty() {
        assertNull(productRepository.find("Sampo Cap Bambang"));
    }

    @Test
    void testFindIfNotExist() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNull(productRepository.find("Sampo Cap Budi"));
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertDoesNotThrow(() -> productRepository.delete("Sampo Cap Bambang"));
        assertNull(productRepository.find("Sampo Cap Bambang"));
    }

    @Test
    void testDeleteIfEmpty() {
        assertThrows(Exception.class, () -> productRepository.delete("Sampo Cap Bambang"));
    }

    @Test
    void testDeleteIfNotExist() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertThrows(Exception.class, () -> productRepository.delete("Sampo Cap Budi"));
    }

    @Test
    void testDeleteIfAlreadyDeleted() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertDoesNotThrow(() -> productRepository.delete("Sampo Cap Bambang"));
        assertThrows(Exception.class, () -> productRepository.delete("Sampo Cap Bambang"));
    }

    @Test
    void testEditName() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertDoesNotThrow(() -> productRepository.editName("Sampo Cap Bambang", "Sampo Cap Budi"));
        assertEquals("Sampo Cap Budi", product.getProductName());
    }

    @Test
    void testEditNameIfEmpty() {
        assertThrows(Exception.class, () -> productRepository.editName("Sampo Cap Bambang", "Sampo Cap Budi"));
    }

    @Test
    void testEditNameIfNotExist() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertDoesNotThrow(() -> productRepository.editName("Sampo Cap Bambang", "Sampo Cap Budi"));
        assertEquals("Sampo Cap Budi", product.getProductName());
    }

    @Test
    void testEditQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertDoesNotThrow(() -> productRepository.editQuantity("Sampo Cap Bambang", 50));
        assertEquals(50, product.getProductQuantity());
    }

    @Test
    void testEditQuantityIfEmpty() {
        assertThrows(Exception.class, () -> productRepository.editQuantity("Sampo Cap Bambang", 50));
    }

    @Test
    void testEditQuantityIfNotExist() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertThrows(Exception.class, () -> productRepository.editQuantity("Sampo Cap Budi", 50));
    }
}
