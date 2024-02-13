package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith({MockitoExtension.class})
public class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
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

        when(productRepository.create(product)).thenReturn(product);
        Product productFromCreate = productService.create(product);
        assertEquals(product, productFromCreate);
        assertEquals(product.getProductId(), productFromCreate.getProductId());
        assertEquals(product.getProductName(), productFromCreate.getProductName());
        assertEquals(product.getProductQuantity(), productFromCreate.getProductQuantity());

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> productListFromFindAll = productService.findAll();
        Product productFromList = productListFromFindAll.getFirst();
        assertEquals(productFromList, product);
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> productListFromFindAll = productService.findAll();
        assertTrue(productListFromFindAll.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> productListFromFindAll = productService.findAll();
        assertEquals(product1, productListFromFindAll.getFirst());
        assertEquals(product2, productListFromFindAll.getLast());
        assertEquals(2, productListFromFindAll.size());
    }

    @Test
    void testFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.find("Sampo Cap Bambang")).thenReturn(product);
        Product productFromFind = productService.find("Sampo Cap Bambang");
        assertEquals(product, productFromFind);
    }

    @Test
    void testFindIfNotExist() {
        when(productRepository.find("Sampo Cap Budi")).thenReturn(null);
        assertNull(productService.find("Sampo Cap Budi"));
    }

    @Test
    void testDelete() throws NoSuchElementException {
        doNothing().when(productRepository).delete("Sampo Cap Bambang");
        assertDoesNotThrow(() -> productService.delete("Sampo Cap Bambang"));
    }

    @Test
    void testDeleteIfNotExist() throws NoSuchElementException {
        doThrow(NoSuchElementException.class).when(productRepository).delete("Sampo Cap Bambang");
        assertThrows(NoSuchElementException.class, () -> productService.delete("Sampo Cap Bambang"));
    }

    @Test
    void testEditName() throws NoSuchElementException {
        doNothing().when(productRepository).editName("Sampo Cap Bambang", "Sampo Cap Budi");
        assertDoesNotThrow(() -> productService.editName("Sampo Cap Bambang", "Sampo Cap Budi"));
    }

    @Test
    void testEditNameIfNotExist() throws NoSuchElementException {
        doThrow(new NoSuchElementException()).when(productRepository).editName("Sampo Cap Bambang", "Sampo Cap Budi");
        assertThrows(NoSuchElementException.class, () -> productService.editName("Sampo Cap Bambang", "Sampo Cap Budi"));
    }

    @Test
    void testEditQuantity() throws NoSuchElementException {
        doNothing().when(productRepository).editQuantity("Sampo Cap Bambang", 50);
        assertDoesNotThrow(() -> productService.editQuantity("Sampo Cap Bambang", 50));
    }

    @Test
    void testEditQuantityIfNotExist() throws NoSuchElementException {
        doThrow(new NoSuchElementException()).when(productRepository).editQuantity("Sampo Cap Bambang", 50);
        assertThrows(NoSuchElementException.class, () -> productService.editQuantity("Sampo Cap Bambang", 50));
    }
}
