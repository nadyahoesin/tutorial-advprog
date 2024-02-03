package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name="productName") String productName, Model model) {
        service.delete(productName);
        return "redirect:list";
    }

    @GetMapping("/edit")
    public String editProductPage(@RequestParam(name="productName") String productName, Model model) {
        Product product = service.find(productName);
        model.addAttribute("product", product);
        model.addAttribute("throwAwayProduct", new Product());
        return "editProduct";
    }

    @PostMapping("/editName")
    public String editProductName(@RequestParam(name="oldProductName") String productName,
                                  @ModelAttribute Product throwAwayProduct,
                                  Model model) {
        String newProductName = throwAwayProduct.getProductName();
        service.editName(productName, newProductName);
        return "redirect:list";
    }

    @PostMapping("/editQuantity")
    public String editProductQuantity(@RequestParam(name="oldProductName") String productName,
                                  @ModelAttribute Product throwAwayProduct,
                                  Model model) {
        int newProductQuantity = throwAwayProduct.getProductQuantity();
        service.editQuantity(productName, newProductQuantity);
        return "redirect:list";
    }
}
