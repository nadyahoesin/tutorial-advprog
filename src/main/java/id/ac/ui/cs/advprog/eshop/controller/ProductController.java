package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "listProduct";
    }

    @GetMapping("/delete/{productName}")
    public String deleteProduct(@PathVariable String productName, Model model) {
        service.delete(productName);
        return "listProduct";
    }

    @GetMapping("/edit/{productName}")
    public String editProductPage(@PathVariable String productName, Model model) {
        Product product = service.find(productName);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{productName}")
    public String editProductName(@ModelAttribute String newProductName, Model model) {
        service.editName(newProductName);
        return "listProduct";
    }
}
