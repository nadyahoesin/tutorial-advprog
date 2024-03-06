package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService service;

    @GetMapping("/detail")
    public String getDetails(Model model) {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "");
        Payment payment = new Payment("", "Voucher", paymentData);
        model.addAttribute(payment);
        return "GetPaymentDetails";
    }

    @GetMapping("/detail/{paymentId}")
    public String showDetails(@PathVariable("paymentId") String paymentId, Model model) {
        Payment payment = this.service.getPayment(paymentId);
        model.addAttribute(payment);
        return "PaymentDetails";
    }

    @GetMapping("/admin/list")
    public String adminGetPayments(Model model) {
        List<Payment> payments = this.service.getAllPayments();
        model.addAttribute(payments);
        return "AdminPaymentsList";
    }

    @GetMapping("/admin/detail/{paymentId}")
    public String adminGetDetails(@PathVariable("paymentId") String paymentId, Model model) {
        Payment payment = this.service.getPayment(paymentId);
        model.addAttribute(payment);
        return "PaymentDetails";
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String adminSetStatus(@RequestParam(name="status") String status,
                                 @PathVariable("paymentId") String paymentId, Model model) {
        Payment payment = this.service.getPayment(paymentId);
        this.service.setStatus(payment, status);
        return String.format("redirect:/admin/detail/%s", paymentId);
    }
}
