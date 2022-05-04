package service;

import entity.Order;
import repository.OrderRepository;

import java.util.List;

public class OrderService {
    OrderRepository orderRepository = new OrderRepository();

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public void update(Order order) {
        orderRepository.update(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllOfAnExpert(Long id) {
        return orderRepository.findAllOfAnExpert(id);
    }

}
