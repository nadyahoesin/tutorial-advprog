package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private Map<Payment, Order> payments;

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        return null;
    }

    public Payment getPayment(String paymentId) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return null;
    }

    public Order getOrderOfPayment(String paymentId) {
        return null;
    }
}
