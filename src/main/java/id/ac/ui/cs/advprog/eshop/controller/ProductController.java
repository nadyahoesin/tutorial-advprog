package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String welcomePage(Model model) {
        return "welcomePage";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "listProduct";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@RequestParam(name="productName") String productName, Model model) throws NoSuchElementException {
        service.delete(productName);
        return "redirect:list";
    }

    @GetMapping("/product/edit")
    public String editProductPage(@RequestParam(name="productName") String productName, Model model) {
        Product product = service.find(productName);
        model.addAttribute("product", product);
        model.addAttribute("throwAwayProduct", new Product());
        return "editProduct";
    }

    @PostMapping("/product/editName")
    public String editProductName(@RequestParam(name="oldProductName") String productName,
                                  @ModelAttribute Product throwAwayProduct,
                                  Model model) throws NoSuchElementException {
        String newProductName = throwAwayProduct.getProductName();
        service.editName(productName, newProductName);
        return "redirect:list";
    }

    @PostMapping("/product/editQuantity")
    public String editProductQuantity(@RequestParam(name="oldProductName") String productName,
                                  @ModelAttribute Product throwAwayProduct,
                                  Model model) throws NoSuchElementException {
        int newProductQuantity = throwAwayProduct.getProductQuantity();
        service.editQuantity(productName, newProductQuantity);
        return "redirect:list";
    }
}
