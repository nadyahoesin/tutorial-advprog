package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @MockBean
    ProductService productService;

    @MockBean
    CarService carService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testWelcomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProductPage() throws Exception {
        this.mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.create(product)).thenReturn(product);
        this.mockMvc.perform(post("/product/create", product))
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testProductListPage() throws Exception {
        this.mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProduct() throws Exception {
        this.mockMvc.perform(get("/product/delete")
                        .param("productName", "Sampo Cap Bambang"))
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testDeleteProductIfNotExist() {
        doThrow(new NoSuchElementException()).when(productService).delete("Sampo Cap Budi");
        assertThrows(Exception.class, () -> this.mockMvc.perform(get("/product/delete")
                        .param("productName", "Sampo Cap Budi")));
    }

    @Test
    void testEditProductName() throws Exception {
        Product throwAwayProduct = new Product();
        throwAwayProduct.setProductName("Sampo Cap Budi");

        this.mockMvc.perform(post("/product/editName", throwAwayProduct)
                        .param("oldProductName", "Sampo Cap Bambang"))
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testEditProductQuantity() throws Exception {
        Product throwAwayProduct = new Product();
        throwAwayProduct.setProductQuantity(100);

        this.mockMvc.perform(post("/product/editQuantity", throwAwayProduct)
                        .param("oldProductName", "Sampo Cap Bambang"))
                .andExpect(redirectedUrl("list"));
    }
}
