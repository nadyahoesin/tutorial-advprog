package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private PaymentService paymentService;

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

    @GetMapping("/pay/{orderId}")
    public String payOrder(@PathVariable("orderId") String orderId, Model model) {
        Order order = this.service.findById(orderId);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "");
        Payment payment = new Payment("", "Voucher", paymentData);
        model.addAttribute("order", order);
        model.addAttribute("payment", payment);
        return "PayOrder";
    }

    @PostMapping("/pay/{orderId}")
    public String paymentDone(@PathVariable("orderId") String orderId,
                              @ModelAttribute Payment payment,
                              Model model) {
        Order order = this.service.findById(orderId);
        Payment createdPayment = this.paymentService.addPayment(order, payment.getMethod(),
                payment.getPaymentData());
        model.addAttribute("payment", createdPayment);
        return "PaymentDone";
    }
}
