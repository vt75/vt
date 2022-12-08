package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderServise {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServise(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Данный метод позволяет вернуть все заказы
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    //Данный метод позволяет вернуть заказ по id(4 последние буквы)
    public Order getOrderId(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    //Данный метод позволяет обновить данные товара
    @Transactional
    public void updateOrder(int id, Order order) {
        order.setId(id);
        orderRepository.save(order);
    }

    //Данный метод позволяет удалить товар по id
    @Transactional
    public void deleteOrder(int id) {orderRepository.deleteById(id);}
}
