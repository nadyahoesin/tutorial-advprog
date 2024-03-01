package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {
    private Map<Payment, Order> payments = new HashMap<>();

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(this.generateId(), method, paymentData);
        this.payments.put(payment, order);
        return payment;
    }

    public Payment getPayment(String paymentId) {
        for (Payment payment : this.payments.keySet()) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }

        return null;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(this.payments.keySet());
    }

    public Order getOrderOfPayment(String paymentId) {
        Payment payment = this.getPayment(paymentId);

        if (payment == null) {
            throw new IllegalArgumentException();
        }

        return payments.get(payment);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
