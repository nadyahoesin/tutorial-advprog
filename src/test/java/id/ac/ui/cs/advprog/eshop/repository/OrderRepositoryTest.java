package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {
    OrderRepository orderRepository;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        this.orderRepository = new OrderRepository();

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
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697cbf1e",
                products, 1708560000L, "Bambang Sudrajat");
        this.orders.add(order3);
    }

    @Test
    void testSaveCreate() {
        Order order = this.orders.get(1);
        Order result = this.orderRepository.save(order);

        Order findResult = this.orderRepository.findById(order.getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Order order = this.orders.get(1);
        this.orderRepository.save(order);

        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
                order.getAuthor(), OrderStatus.SUCCESS.getValue());
        Order result = this.orderRepository.save(newOrder);

        Order findResult = this.orderRepository.findById(order.getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindIdIfIdFound() {
        for (Order order : this.orders) {
            this.orderRepository.save(order);
        }

        Order findResult = this.orderRepository.findById(this.orders.get(1).getId());
        assertEquals(this.orders.get(1).getId(), findResult.getId());
        assertEquals(this.orders.get(1).getOrderTime(), findResult.getOrderTime());
        assertEquals(this.orders.get(1).getAuthor(), findResult.getAuthor());
        assertEquals(this.orders.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindIdIfIdNotFound() {
        for (Order order : this.orders) {
            this.orderRepository.save(order);
        }

        Order findResult = this.orderRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        for (Order order : this.orders) {
            this.orderRepository.save(order);
        }

        List<Order> orderList = this.orderRepository.findAllByAuthor(
                this.orders.get(1).getAuthor());
        assertEquals(2, orderList.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        this.orderRepository.save(this.orders.get(1));

        List<Order> orderList = this.orderRepository.findAllByAuthor(
                this.orders.get(1).getAuthor().toLowerCase());
        assertTrue(orderList.isEmpty());
    }
}
