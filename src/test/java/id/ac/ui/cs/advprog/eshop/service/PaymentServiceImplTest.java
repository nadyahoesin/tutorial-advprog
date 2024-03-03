package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Order> orders;
    List<Map<String, String>> somePaymentData;
    List<Payment> payments;

//    @BeforeEach
//    void setUp() {
//        Product product = new Product();
//        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
//        product.setProductName("Sampo Cap Bambang");
//        product.setProductQuantity(2);
//
//        List<Product> products = new ArrayList<>();
//        products.add(product);
//
//        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
//                products, 1708560000L, "Safira Sudrajat");
//        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
//                products, 1708560000L, "Safira Sudrajat");
//        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697cbf1e",
//                products, 1708560000L, "Bambang Sudrajat");
//
//        this.orders = new ArrayList<>();
//        this.orders.add(order1);
//        this.orders.add(order2);
//        this.orders.add(order3);
//
//        Map<String, String> paymentData1 = new HashMap<>();
//        paymentData1.put("voucherCode", "ESHOP1234ABC5678");;
//
//        Map<String, String> paymentData2 = new HashMap<>();
//        paymentData2.put("address", "Jakarta");
//        paymentData2.put("deliveryFee", "Rp20.000");
//
//        Map<String, String> paymentData3 = new HashMap<>();
//        paymentData3.put("voucherCode", "E");
//
////        this.somePaymentData.add(paymentData1);
////        this.somePaymentData.add(paymentData2);
////        this.somePaymentData.add(paymentData3);
//
//        Payment payment1 = new Payment("1234abc", "Voucher", paymentData1);
//        Payment payment2 = new Payment("1235abc", "COD", paymentData2);
//        Payment payment3 = new Payment("1236abc", "Voucher", paymentData3);
//
//        this.payments = new ArrayList<>();
//        this.payments.add(payment1);
//        this.payments.add(payment2);
//        this.payments.add(payment3);
//
//    }
//
//    @Test
//    void testAddPaymentVoucher() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.getFirst();
//        doReturn(payment).when(paymentRepository).addPayment(order, payment);
//
//        Payment addedPayment = this.paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
//
//        assertEquals(payment.getId(), addedPayment.getId());
//        assertEquals(payment.getMethod(), addedPayment.getMethod());
//        assertEquals(payment.getPaymentData().keySet(), addedPayment.getPaymentData().keySet());
//        verify(this.paymentRepository, times(1)).addPayment(order, payment);
//    }
//
//    @Test
//    void testAddPaymentInvalidMethod() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.getFirst();
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, "Credit", payment.getPaymentData());
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentVoucherNoVoucherCodeKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.getFirst();
//        Map<String, String> paymentData = new HashMap<>();
//
//        assertThrows(IllegalArgumentException.class, () -> {
//           this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentVoucherIncorrectVoucherCodeKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.getFirst();
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("kodeVoucher", "ESHOP1234ABC5678");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentCODNoAddressKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.get(1);
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("deliveryFee", "Rp20.000");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentCODNoDeliveryFeeKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.get(1);
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("address", "Jakarta");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentCODIncorrectAddressKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.get(1);
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("alamat", "Jakarta");
//        paymentData.put("deliveryFee", "Rp20.000");
//        doReturn(payment).when(paymentRepository).addPayment(order, payment);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//    @Test
//    void testAddPaymentCODIncorrectDeliveryFeeKey() {
//        Order order = this.orders.getFirst();
//        Payment payment = this.payments.get(1);
//        Map<String, String> paymentData = new HashMap<>();
//        paymentData.put("address", "Jakarta");
//        paymentData.put("ongkir", "Rp20.000");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.paymentService.addPayment(order, payment.getMethod(), paymentData));
//        });
//
//        verify(this.paymentRepository, times(0))
//                .addPayment(any(Order.class), any(Payment.class));
//    }
//
//
//    void testSetStatusSuccess() {
//        Payment payment = this.payments.getFirst();
//        Order order = this.orders.getFirst();
//        doReturn(order).when(this.paymentRepository).getOrderOfPayment(payment.getId());
//
//        Payment paymentFromSetStatus = this.paymentService.setStatus(payment, "SUCCESS");
//
//
//
//    }
//
//    void testGetPayment(String paymentId) {
//        return null;
//    }
//
//    @Override
//    void testGetAllPayments() {
//        return null;
//    }


}
