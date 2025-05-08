package com.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.entity.Art;
import com.entity.Customer;
import com.entity.Order;
import com.service.ArtService;
import com.service.OrderService;
import com.serviceimpl.ArtServiceImpl;
import com.serviceimpl.OrderServiceImpl;

public class OrderController {

    private static final Scanner sc = new Scanner(System.in);
    private static final OrderService orderService = new OrderServiceImpl();
    private static final ArtService artService = new ArtServiceImpl();

    // ====================== ADMIN PANEL ORDER METHODS =========================

    // ✅ View all orders (For Admin)
    public static void viewAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders != null && !orders.isEmpty()) {
            System.out.println("===== All Orders =====");
            for (Order order : orders) {
                // Displaying each order's details
                System.out.println("Order ID: " + order.getId());
                System.out.println("Customer: " + order.getCustomer().getFullName());
                System.out.println("Art: " + order.getArt().getTitle());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Quantity: " + order.getQuantity());
                System.out.println("Total Price: " + order.getTotalPrice());
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("⚠️ No orders found.");
        }
    }

    // ✅ View orders by a specific customer (used in both panels)
    public static void viewOrdersByCustomer(Customer customer) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);
        if (orders != null && !orders.isEmpty()) {
            System.out.println("===== Orders for Customer: " + customer.getFullName() + " =====");
            for (Order order : orders) {
                // Displaying each order made by this customer
                System.out.println("Order ID: " + order.getId());
                System.out.println("Art: " + order.getArt().getTitle());
                System.out.println("Date: " + order.getOrderDate());
                System.out.println("Quantity: " + order.getQuantity());
                System.out.println("Total: " + order.getTotalPrice());
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("⚠️ No orders found for this customer.");
        }
    }

    // ✅ Delete order by ID (Admin)
    public static void deleteOrder() {
        System.out.print("Enter Order ID to delete: ");
        int orderId = sc.nextInt();

        // Check if order exists
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            // Proceed to delete if found
            orderService.deleteOrder(orderId);
            System.out.println("✅ Order deleted successfully.");
        } else {
            System.out.println("⚠️ Order not found.");
        }
    }

    // ====================== CUSTOMER PANEL ORDER METHODS =========================

    // ✅ Place a new order (Customer)
    public static void placeOrder(Customer customer) {
        System.out.print("Enter Art ID to purchase: ");
        int artId = sc.nextInt();
        Art art = artService.getArtById(artId); // Fetch the selected artwork

        if (art == null) {
            System.out.println("⚠️ Art not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println("⚠️ Invalid quantity.");
            return;
        }

        // Calculate total price
        double totalPrice = art.getPrice() * qty;

        // Create a new Order object
        Order order = new Order();
        order.setCustomer(customer);
        order.setArt(art);
        order.setOrderDate(LocalDate.now()); // Set current date as order date
        order.setQuantity(qty);
        order.setTotalPrice(totalPrice);

        // Save order to database
        orderService.saveOrder(order);
        System.out.println("✅ Order placed successfully.");
    }

    // ✅ View orders placed by the currently logged-in customer
    public static void viewMyOrders(Customer customer) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);
        if (orders != null && !orders.isEmpty()) {
            System.out.println("===== My Orders =====");
            for (Order order : orders) {
                // Displaying order details
                System.out.println("Order ID: " + order.getId());
                System.out.println("Art: " + order.getArt().getTitle());
                System.out.println("Date: " + order.getOrderDate());
                System.out.println("Quantity: " + order.getQuantity());
                System.out.println("Total: " + order.getTotalPrice());
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("⚠️ You have not placed any orders yet.");
        }
    }
}
