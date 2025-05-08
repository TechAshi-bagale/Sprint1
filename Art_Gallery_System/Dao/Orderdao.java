package com.dao;

import com.entity.Customer;
import com.entity.Order;
import java.util.List;

/**
 * Data Access Object interface for Order operations.
 */
public interface Orderdao {

    // Save a new order
    void saveOrder(Order order);

    // Update an existing order
    void updateOrder(Order order);

    // Delete order by ID
    void deleteOrder(int orderId);

    // Get order by ID
    Order getOrderById(int orderId);

    // Get all orders (Admin use)
    List<Order> getAllOrders();

    // Get orders placed by a specific customer (Customer use)
    List<Order> getOrdersByCustomerId(Customer customer);
}
