package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddPaymentVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234abc", "Voucher", paymentData);
        assertEquals("1234abc", payment.getId());
        assertEquals("Voucher", payment.getMethod());
        assertEquals(paymentData.get("voucherCode"), payment.getPaymentData().get("voucherCode"));
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentCOD() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("deliveryFee", "Rp20.000");

        Payment payment = new Payment("1234abc", "COD", paymentData);
        assertEquals("1234abc", payment.getId());
        assertEquals("COD", payment.getMethod());
        assertEquals(paymentData.get("address"), payment.getPaymentData().get("address"));
        assertEquals(paymentData.get("deliveryFee"), payment.getPaymentData().get("deliveryFee"));
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "Credit", paymentData);
        });
    }

    @Test
    void testAddPaymentVoucherNoVoucherCodeKey() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "Voucher", paymentData);
        });
    }

    @Test
    void testAddPaymentVoucherIncorrectVoucherCodeKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("kodeVoucher", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "Voucher", paymentData);
        });
    }

    @Test
    void testAddPaymentCODNoAddressKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "COD", paymentData);
        });
    }

    @Test
    void testAddPaymentCODNoDeliveryFeeKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "COD", paymentData);
        });
    }

    @Test
    void testAddPaymentCODIncorrectAddressKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("alamat", "Jakarta");
        paymentData.put("deliveryFee", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "COD", paymentData);
        });
    }

    @Test
    void testAddPaymentCODIncorrectDeliveryFeeKey() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("ongkir", "Rp20.000");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234abc", "COD", paymentData);
        });
    }

    @Test
    void testAddPaymentVoucherIncorrectVoucherCodeLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "E");

        Payment payment = new Payment("1234abc", "Voucher", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentVoucherIncorrectVoucherCodePrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHPO1234ABC5678");

        Payment payment = new Payment("1234abc", "Voucher", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentVoucherIncorrectVoucherCodeNumCharAmount() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234AB56789");

        Payment payment = new Payment("1234abc", "Voucher", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCODNullAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "Rp20.000");

        Payment payment = new Payment("1234abc", "COD", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCODEmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "Rp20.000");

        Payment payment = new Payment("1234abc", "COD", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCODNullDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("1234abc", "COD", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentCODEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("1234abc", "COD", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
