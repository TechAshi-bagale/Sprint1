package com.service;

import com.entity.Customer;
import com.entity.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int orderId);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByCustomer(Customer customer);
}
