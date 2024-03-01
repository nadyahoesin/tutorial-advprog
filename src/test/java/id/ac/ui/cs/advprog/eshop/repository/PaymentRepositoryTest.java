package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Product> products;
    List<Order> orders;
    List<Map<String, String>> somePaymentData;

    @BeforeEach
    void setUp() {
        this.paymentRepository = new PaymentRepository();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        this.products = new ArrayList<>();
        this.products.add(product);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                this.products, 1708560000L, "Safira Sudrajat");

        this.orders = new ArrayList<>();
        this.orders.add(order1);
        this.orders.add(order2);

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");;

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jakarta");
        paymentData2.put("deliveryFee", "Rp20.000");

        this.somePaymentData = new ArrayList<>();
        this.somePaymentData.add(paymentData1);
        this.somePaymentData.add(paymentData2);
    }

    @Test
    void testAddPayment() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        Order order = new Order("e334ef40-9eff-4da8-9487-8ee697cbf1e",
                this.products, 1708560000L, "Bambang Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5679");

        Payment addedPayment = this.paymentRepository.addPayment(order, "Voucher", paymentData);

        assertEquals("Voucher", addedPayment.getMethod());
        assertEquals(paymentData.keySet(), addedPayment.getPaymentData().keySet());
        assertEquals(paymentData.get("voucherCode"), addedPayment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testGetPayment() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        Order order = new Order("e334ef40-9eff-4da8-9487-8ee697cbf1e",
                this.products, 1708560000L, "Bambang Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5679");

        Payment addedPayment = this.paymentRepository.addPayment(order, "Voucher", paymentData);
        String paymentId = addedPayment.getId();

        Payment paymentFromGet = this.paymentRepository.getPayment(paymentId);

        assertEquals(paymentId, paymentFromGet.getId());
        assertEquals(addedPayment.getMethod(), paymentFromGet.getMethod());
        assertEquals(addedPayment.getPaymentData().keySet(), paymentFromGet.getPaymentData().keySet());
        assertEquals(addedPayment.getPaymentData().get("voucherCode"), paymentFromGet.getPaymentData().get("voucherCode"));
    }

    @Test
    void testGetPaymentIdNotFound() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        Payment paymentFromGet = this.paymentRepository.getPayment("zczc");
        assertNull(paymentFromGet);
    }

    @Test
    void testGetAllPayments() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        List<Payment> allPayments = this.paymentRepository.getAllPayments();
        assertEquals(2, allPayments.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> allPayments = this.paymentRepository.getAllPayments();
        assertTrue(allPayments.isEmpty());
    }

    @Test
    void testGetOrderOfPayment() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        Order order = new Order("e334ef40-9eff-4da8-9487-8ee697cbf1e",
                this.products, 1708560000L, "Bambang Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5679");

        Payment addedPayment = this.paymentRepository.addPayment(order, "Voucher", paymentData);
        Order orderFromGet = this.paymentRepository.getOrderOfPayment(addedPayment.getId());

        assertEquals(order.getId(), orderFromGet.getId());
        assertEquals(order.getOrderTime(), orderFromGet.getOrderTime());
        assertEquals(order.getAuthor(), orderFromGet.getAuthor());
        assertEquals(order.getStatus(), orderFromGet.getStatus());
    }

    @Test
    void testGetOrderOfPaymentInvalidId() {
        this.paymentRepository.addPayment(this.orders.get(0), "Voucher", this.somePaymentData.get(0));
        this.paymentRepository.addPayment(this.orders.get(1), "COD", this.somePaymentData.get(1));

        assertThrows(IllegalArgumentException.class, () -> this.paymentRepository.getOrderOfPayment("zczc"));
    }
}