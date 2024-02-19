package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService service;

    private static final String LIST_REDIRECTION = "redirect:list";

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
        return LIST_REDIRECTION;
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
        return LIST_REDIRECTION;
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
        return LIST_REDIRECTION;
    }

    @PostMapping("/product/editQuantity")
    public String editProductQuantity(@RequestParam(name="oldProductName") String productName,
                                  @ModelAttribute Product throwAwayProduct,
                                  Model model) throws NoSuchElementException {
        int newProductQuantity = throwAwayProduct.getProductQuantity();
        service.editQuantity(productName, newProductQuantity);
        return LIST_REDIRECTION;
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String createCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String createCarPost(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}