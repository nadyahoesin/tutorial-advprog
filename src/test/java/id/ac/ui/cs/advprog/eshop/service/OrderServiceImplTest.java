package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        this.orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708560000L, "Safira Sudrajat");
        this.orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = this.orders.get(1);
        doReturn(order).when(this.orderRepository).save(order);

        Order result = this.orderService.createOrder(order);
        verify(this.orderRepository, times(1)).save(order);
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testCreateOrderIfAlreadyExist() {
        Order order = this.orders.get(1);
        doReturn(order).when(this.orderRepository).findById(order.getId());

        assertNull(this.orderService.createOrder(order));
        verify(this.orderRepository, times(0)).save(order);
    }

    @Test
    void testUpdateStatus() {
        Order order = this.orders.get(1);
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
                order.getAuthor(), OrderStatus.SUCCESS.getValue());
        doReturn(order).when(this.orderRepository).findById(order.getId());
        doReturn(newOrder).when(this.orderRepository).save(any(Order.class));

        Order result = this.orderService.updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());

        assertEquals(order.getId(), result.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getStatus());
        verify(this.orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = this.orders.get(1);
        doReturn(order).when(this.orderRepository).findById(order.getId());

        assertThrows(IllegalArgumentException.class,
                () -> this.orderService.updateStatus(order.getId(), "MEOW"));

        verify(this.orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidOrderId() {
        doReturn(null).when(this.orderRepository).findById("zczc");

        assertThrows(IllegalArgumentException.class,
                () -> this.orderService.updateStatus("zczc", OrderStatus.SUCCESS.getValue()));

        verify(this.orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testFindIdIfIdFound() {
        Order order = this.orders.get(1);
        doReturn(order).when(this.orderRepository).findById(order.getId());

        Order result = this.orderService.findById(order.getId());
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        doReturn(null).when(this.orderRepository).findById("zczc");
        assertNull(this.orderService.findById("zczc"));
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        Order order = this.orders.get(1);
        doReturn(this.orders).when(this.orderRepository).findAllByAuthor(order.getAuthor());

        List<Order> results = this.orderService.findAllByAuthor(order.getAuthor());
        for (Order result : results) {
            assertEquals(order.getAuthor(), result.getAuthor());
        }

        assertEquals(2, results.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        Order order = this.orders.get(1);
        doReturn(new ArrayList<Order>()).when(this.orderRepository)
                .findAllByAuthor(order.getAuthor().toLowerCase());

        List<Order> results = this.orderService.findAllByAuthor(
                order.getAuthor().toLowerCase());
        assertTrue(results.isEmpty());
    }
}