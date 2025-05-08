package com.controller;

import com.entity.Customer;
import com.service.CustomerService;
import com.serviceimpl.CustomerServiceImpl;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    // Service layer to handle customer operations
    private final static CustomerService customerService = new CustomerServiceImpl();

    // Scanner for general input
    private final static Scanner sc = new Scanner(System.in);

    // Console for hidden password input (may return null in some IDEs)
    private final static Console console = System.console();

    // ========================= Customer Registration =========================
    public static void registerCustomer() {
        System.out.println("\n--- Customer Registration ---");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        // Password input (hidden if console available)
        String password;
        if (console != null) {
            char[] passChars = console.readPassword("Enter password: ");
            password = new String(passChars);
        } else {
            // Fallback for IDEs where console is null
            System.out.print("Enter password (console not available): ");
            password = sc.nextLine();
        }

        System.out.print("Enter full name: ");
        String fullName = sc.nextLine().trim();

        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine().trim();

        // Basic validation to ensure all fields are filled
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {
            System.out.println("❌ All fields are required. Registration failed.");
            return;
        }

        // Create a new customer and register
        Customer customer = new Customer(username, password, fullName, email, phone);
        customerService.registerCustomer(customer);
        System.out.println("✅ Customer registered successfully!");
    }

    // ========================= Customer Login =========================
    public static Customer loginCustomer() {
        System.out.println("\n--- Customer Login ---");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        // Password input
        String password;
        if (console != null) {
            char[] passChars = console.readPassword("Enter password: ");
            password = new String(passChars);
        } else {
            System.out.print("Enter password (console not available): ");
            password = sc.nextLine();
        }

        // Call service to authenticate
        Customer customer = customerService.loginCustomer(username, password);
        if (customer != null) {
            System.out.println("✅ Login successful. Welcome, " + customer.getFullName() + "!");
        } else {
            System.out.println("❌ Invalid username or password.");
        }

        return customer;
    }

    // ========================= Admin: View All Customers =========================
    public void viewAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();

        // Check if the list is empty
        if (customers.isEmpty()) {
            System.out.println("⚠️ No customers found.");
            return;
        }

        // Display customer info
        System.out.println("\n--- Registered Customers ---");
        for (Customer c : customers) {
            System.out.println("ID: " + c.getId() + ", Username: " + c.getUsername() +
                    ", Name: " + c.getFullName() + ", Email: " + c.getEmail() +
                    ", Phone: " + c.getPhone());
        }
    }

    // ========================= Customer: Update Own Info =========================
    public void updateCustomer(Customer customer) {
        System.out.println("\n--- Update Customer Info ---");

        // Prompt new details
        System.out.print("Enter new full name: ");
        String fullName = sc.nextLine().trim();

        System.out.print("Enter new email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine().trim();

        // Update customer fields
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);

        // Call service to save updates
        if (customerService.updateCustomer(customer)) {
            System.out.println("✅ Customer updated successfully.");
        } else {
            System.out.println("❌ Failed to update customer.");
        }
    }

    // ========================= Admin: Delete Customer by ID =========================
    public void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        
        // Validate integer input
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input. Enter a valid ID: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine(); // consume newline character

        // Attempt to delete customer
        if (customerService.deleteCustomer(id)) {
            System.out.println("✅ Customer deleted.");
        } else {
            System.out.println("❌ No customer found with that ID.");
        }
    }

    // ========================= Admin Panel: Customer Management Menu =========================
    public static void manageCustomers() {
        int choice;
        CustomerController controller = new CustomerController(); // Instance for non-static methods

        do {
            System.out.println("\n👥 Customer Management Menu:");
            System.out.println("1. View All Customers");
            System.out.println("2. Delete Customer");
            System.out.println("3. Back to Admin Panel");

            System.out.print("Enter your choice: ");
            
            // Validate menu choice input
            while (!sc.hasNextInt()) {
                System.out.print("❌ Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            // Execute based on user selection
            switch (choice) {
                case 1:
                    controller.viewAllCustomers();
                    break;
                case 2:
                    controller.deleteCustomer();
                    break;
                case 3:
                    System.out.println("🔙 Returning to Admin Panel...");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 3);
    }
}
