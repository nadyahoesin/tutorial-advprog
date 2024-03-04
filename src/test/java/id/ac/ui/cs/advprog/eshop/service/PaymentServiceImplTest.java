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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        List<Product> products = new ArrayList<>();
        products.add(product);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");;

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jakarta");
        paymentData2.put("deliveryFee", "Rp20.000");

        Payment payment1 = new Payment("1234abc", "Voucher", paymentData1);
        Payment payment2 = new Payment("1235abc", "COD", paymentData2);

        this.payments = new ArrayList<>();
        this.payments.add(payment1);
        this.payments.add(payment2);
    }

    @Test
    void testAddPaymentVoucher() {
        Payment payment = this.payments.getFirst();

        Payment addedPayment = this.paymentService.addPayment(this.order, payment.getMethod(), payment.getPaymentData());

        assertEquals(payment.getMethod(), addedPayment.getMethod());
        assertEquals(payment.getPaymentData().keySet(), addedPayment.getPaymentData().keySet());
        verify(this.paymentRepository, times(1))
                .addPayment(eq(this.order), any(Payment.class));
    }

    @Test
    void testAddPaymentInvalidMethod() {
        Payment payment = this.payments.getFirst();

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, "Credit", payment.getPaymentData()));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentVoucherNoVoucherCodeKey() {
        Payment payment = this.payments.getFirst();
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentVoucherIncorrectVoucherCodeKey() {
        Payment payment = this.payments.getFirst();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("kodeVoucher", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentCODNoAddressKey() {
        Payment payment = this.payments.get(1);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentCODNoDeliveryFeeKey() {
        Payment payment = this.payments.get(1);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentCODIncorrectAddressKey() {
        Payment payment = this.payments.get(1);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("alamat", "Jakarta");
        paymentData.put("deliveryFee", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }

    @Test
    void testAddPaymentCODIncorrectDeliveryFeeKey() {
        Payment payment = this.payments.get(1);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("ongkir", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.addPayment(this.order, payment.getMethod(), paymentData));

        verify(this.paymentRepository, times(0))
                .addPayment(any(Order.class), any(Payment.class));
    }


    @Test
    void testSetStatusSuccess() {
        Payment payment = this.payments.getFirst();
        doReturn(this.order).when(this.paymentRepository).getOrderOfPayment(payment.getId());

        Payment paymentFromSetStatus = this.paymentService.setStatus(payment, "SUCCESS");

        assertEquals(payment.getMethod(), paymentFromSetStatus.getMethod());
        assertEquals(payment.getPaymentData().keySet(), paymentFromSetStatus.getPaymentData().keySet());
        assertEquals("SUCCESS", paymentFromSetStatus.getStatus());
        verify(this.paymentRepository, times(1))
                .getOrderOfPayment(paymentFromSetStatus.getId());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = this.payments.getFirst();
        doReturn(this.order).when(this.paymentRepository).getOrderOfPayment(payment.getId());

        Payment paymentFromSetStatus = this.paymentService.setStatus(payment, "REJECTED");

        assertEquals(payment.getId(), paymentFromSetStatus.getId());
        assertEquals(payment.getMethod(), paymentFromSetStatus.getMethod());
        assertEquals(payment.getPaymentData().keySet(), paymentFromSetStatus.getPaymentData().keySet());
        assertEquals("REJECTED", paymentFromSetStatus.getStatus());
        verify(this.paymentRepository, times(1))
                .getOrderOfPayment(paymentFromSetStatus.getId());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = this.payments.getFirst();

        assertThrows(IllegalArgumentException.class, () ->
                this.paymentService.setStatus(payment, "MEOW"));

        verify(this.paymentRepository, times(0))
                .getOrderOfPayment(any(String.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = this.payments.getFirst();
        doReturn(payment).when(this.paymentRepository).getPayment(payment.getId());

        Payment paymentFromGet = this.paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), paymentFromGet.getId());
        assertEquals(payment.getMethod(), paymentFromGet.getMethod());
        assertEquals(payment.getPaymentData().keySet(), paymentFromGet.getPaymentData().keySet());
        verify(this.paymentRepository, times(1))
                .getPayment(payment.getId());
    }

    @Test
    void testGetPaymentIdNotFound() {
        doReturn(null).when(this.paymentRepository).getPayment("zczc");

        Payment paymentFromGet = this.paymentService.getPayment("zczc");
        assertNull(paymentFromGet);
        verify(this.paymentRepository, times(1))
                .getPayment("zczc");
    }

    @Test
    void testGetAllPayments() {
        doReturn(this.payments).when(this.paymentRepository).getAllPayments();

        List<Payment> allPayments = this.paymentService.getAllPayments();
        assertEquals(this.payments.size(), allPayments.size());
        verify(this.paymentRepository, times(1)).getAllPayments();
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        doReturn(new ArrayList<>()).when(this.paymentRepository).getAllPayments();

        List<Payment> allPayments = this.paymentService.getAllPayments();
        assertTrue(allPayments.isEmpty());
        verify(this.paymentRepository, times(1)).getAllPayments();
    }
}
