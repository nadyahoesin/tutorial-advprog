package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/create")
    public String createOrderForm(Model model) {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        Order order = new Order("", products, 0L, "");
        model.addAttribute("order", order);
        return "CreateOrder";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order, Model model) {
        order.setId(UUID.randomUUID().toString());
        order.setOrderTime(Instant.now().toEpochMilli());
        order.setStatus(OrderStatus.WAITING_PAYMENT.getValue());
        this.service.createOrder(order);
        return "redirect:history";
    }

    @GetMapping("/history")
    public String getHistoryForm(Model model) {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        Order order = new Order("", products, 0L, "");
        model.addAttribute("order", order);
        return "GetOrderHistory";
    }

    @PostMapping("/history")
    public String getHistory(@ModelAttribute Order order, String author, Model model) {
        List<Order> orders = this.service.findAllByAuthor(order.getAuthor());
        model.addAttribute("orders", orders);
        return "OrderHistory";
    }
}
