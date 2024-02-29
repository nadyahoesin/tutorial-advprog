package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        if (this.orderRepository.findById(order.getId()) == null) {
            this.orderRepository.save(order);
            return order;
        }

        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = this.orderRepository.findById((orderId));
        if (order != null) {
            Order newOrder = new Order(order.getId(), order.getProducts(),
                    order.getOrderTime(), order.getAuthor(), status);
            this.orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Order findById(String orderId) {
        return this.orderRepository.findById(orderId);
    }

    @Override
    public List<Order> findAllByAuthor(String author) {
        return this.orderRepository.findAllByAuthor(author);
    }
}