package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {

    }

    void testAddPayment(Order order, String method, Map<String, String> paymentData) {
        doReturn(Payment)
    }

    void testSetStatus(Payment payment, String status) {
        return null;
    }

    void testGetPayment(String paymentId) {
        return null;
    }

    @Override
    void testGetAllPayments() {
        return null;
    }


}
