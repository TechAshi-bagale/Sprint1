package com.serviceimpl;

import java.util.List;

import com.dao.Orderdao;
import com.daoimpl.OrderDaoImpl;
import com.entity.Customer;
import com.entity.Order;
import com.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private Orderdao orderDAO = new OrderDaoImpl();

    @Override
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderDAO.getOrdersByCustomerId(customer);
    }
}
