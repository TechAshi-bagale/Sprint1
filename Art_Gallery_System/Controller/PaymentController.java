package com.controller;

import com.entity.Payment;
import com.entity.Customer;
import com.entity.Order;
import com.service.PaymentService;
import com.service.OrderService;
import com.serviceimpl.PaymentServiceImpl;
import com.serviceimpl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class for handling Payment operations in both Admin and Customer panels.
 */
public class PaymentController {

    // Services for payment and order operations
    private static final PaymentService paymentService = new PaymentServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private final Scanner sc = new Scanner(System.in);

    /**
     * Admin panel menu to manage payment operations.
     */
    public void managePayments() {
        int choice;
        do {
            System.out.println("\n💵 Payment Management Menu:");
            System.out.println("1. Add Payment");
            System.out.println("2. Update Payment");
            System.out.println("3. Delete Payment");
            System.out.println("4. View All Payments");
            System.out.println("5. View Payment By ID");
            System.out.println("6. Back to Admin Panel");

            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("❌ Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1: addPayment(); break;
                case 2: updatePayment(); break;
                case 3: deletePayment(); break;
                case 4: viewAllPayments(); break;
                case 5: viewPaymentById(); break;
                case 6: System.out.println("🔙 Returning to Admin Panel..."); break;
                default: System.out.println("❌ Invalid choice, try again.");
            }
        } while (choice != 6);
    }

    /**
     * Add a new payment for an existing order.
     */
    private void addPayment() {
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Payment Amount: ");
        double amount = getValidDouble();

        System.out.print("Enter Payment Method (Credit Card / UPI / Cash / Net Banking): ");
        String method = sc.nextLine();

        System.out.print("Enter Order ID: ");
        int orderId = getValidInt();
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            System.out.println("❌ Invalid Order ID. Cannot add Payment.");
            return;
        }

        // Create and save payment
        LocalDate date = LocalDate.now();
        Payment payment = new Payment(amount, date, method, order);
        paymentService.createPayment(payment);

        System.out.println("✅ Payment added successfully!");
    }

    /**
     * Update existing payment details.
     */
    private void updatePayment() {
        System.out.print("Enter Payment ID to Update: ");
        int paymentId = getValidInt();

        Payment existing = paymentService.getPaymentById(paymentId);
        if (existing == null) {
            System.out.println("❌ Payment not found.");
            return;
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter New Amount: ");
        double newAmount = getValidDouble();

        System.out.print("Enter New Payment Method: ");
        String newMethod = sc.nextLine();

        System.out.print("Enter New Order ID: ");
        int newOrderId = getValidInt();
        Order newOrder = orderService.getOrderById(newOrderId);

        if (newOrder == null) {
            System.out.println("❌ Invalid Order ID.");
            return;
        }

        // Set updated fields
        existing.setAmount(newAmount);
        existing.setPaymentMethod(newMethod);
        existing.setOrder(newOrder);
        existing.setCustomer(newOrder.getCustomer());
        existing.setPaymentDate(LocalDate.now());

        paymentService.updatePayment(existing);
        System.out.println("✅ Payment updated successfully!");
    }

    /**
     * Delete a payment by ID.
     */
    private void deletePayment() {
        System.out.print("Enter Payment ID to Delete: ");
        int id = getValidInt();
        paymentService.deletePayment(id);
        System.out.println("✅ Payment deleted successfully (if existed).");
    }

    /**
     * View all payments in the system.
     */
    public static void viewAllPayments() {
        List<Payment> list = paymentService.getAllPayments();
        if (list.isEmpty()) {
            System.out.println("⚠️ No payments found.");
        } else {
            for (Payment p : list) {
                System.out.println(p);
                System.out.println("-----------------------------");
            }
        }
    }

    /**
     * View payment by a specific Payment ID.
     */
    private void viewPaymentById() {
        System.out.print("Enter Payment ID: ");
        int id = getValidInt();
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            System.out.println(payment);
        } else {
            System.out.println("❌ Payment not found.");
        }
    }

    /**
     * Get validated double input from user.
     */
    private double getValidDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("❌ Invalid input. Please enter a valid amount: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    /**
     * Get validated integer input from user.
     */
    private int getValidInt() {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * Allows a customer to make payment for one of their orders.
     */
    public void makePayment(Customer customer) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);

        if (orders.isEmpty()) {
            System.out.println("⚠️ No orders found to make a payment.");
            return;
        }

        System.out.println("📦 Your Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId() + " | Total Amount: " + order.getTotalPrice());
        }

        System.out.print("Enter Order ID you want to pay for: ");
        int orderId = getValidInt();
        Order selectedOrder = orderService.getOrderById(orderId);

        // Validate customer ownership of order
        if (selectedOrder == null || selectedOrder.getCustomer().getId() != customer.getId()) {
            System.out.println("❌ Invalid Order ID.");
            return;
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter Payment Method (Credit Card / UPI / Cash / Net Banking): ");
        String method = sc.nextLine();

        // Create and save payment
        LocalDate date = LocalDate.now();
        Payment payment = new Payment(selectedOrder.getTotalPrice(), date, method, selectedOrder);
        paymentService.createPayment(payment);

        System.out.println("✅ Payment successful!");
    }

    /**
     * Displays all payments made by a specific customer.
     */
    public static void viewCustomerPayments(int customerId) {
        List<Payment> list = paymentService.getAllPayments();
        boolean found = false;

        for (Payment p : list) {
            if (p.getCustomer() != null && p.getCustomer().getId() == customerId) {
                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("⚠️ No payments found for this customer.");
        }
    }
}
