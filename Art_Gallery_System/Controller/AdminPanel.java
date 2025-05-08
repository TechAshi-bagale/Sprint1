package com.controller;

import java.util.Scanner;

public class AdminPanel {

    // Scanner instance for taking user input
    private Scanner sc = new Scanner(System.in);

    // Starts the admin panel loop
    public void startAdminPanel() {
        boolean running = true;

        while (running) {
            // Display admin panel menu
            System.out.println("\n========== Admin Panel ==========");
            System.out.println("1. Manage Artists");
            System.out.println("2. Manage Arts");
            System.out.println("3. Manage Categories");
            System.out.println("4. Manage Customers");
            System.out.println("5. Manage Orders");
            System.out.println("6. Manage Payments");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            int choice = 0;

            // Input validation to ensure user enters a number
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 to 7.");
                continue;
            }

            // Execute appropriate method based on user choice
            switch (choice) {
                case 1:
                    manageArtists(); // Artist management section
                    break;
                case 2:
                    manageArts(); // Art management section
                    break;
                case 3:
                    manageCategories(); // Category management section
                    break;
                case 4:
                    manageCustomers(); // Customer management section
                    break;
                case 5:
                    manageOrders(); // Order management section
                    break;
                case 6:
                    managePayments(); // Payment management section
                    break;
                case 7:
                    System.out.println("Logging out...");
                    running = false; // Exit loop and logout
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 to 7.");
            }
        }
    }

    // Stub method for managing artists
    private void manageArtists() {
        System.out.println("\n--- Manage Artists ---");
        // Call ArtistController.manageArtistMenu() method here
        // Example: new ArtistController().manageArtistMenu();
    }

    // Stub method for managing arts
    private void manageArts() {
        System.out.println("\n--- Manage Arts ---");
        // Call ArtController.manageArtMenu() method here
    }

    // Stub method for managing categories
    private void manageCategories() {
        System.out.println("\n--- Manage Categories ---");
        // Call CategoryController.manageCategoryMenu() method here
    }

    // Stub method for managing customers
    private void manageCustomers() {
        System.out.println("\n--- Manage Customers ---");
        // Call CustomerController.manageCustomerMenu() method here
    }

    // Stub method for managing orders
    private void manageOrders() {
        System.out.println("\n--- Manage Orders ---");
        // Call OrderController.manageOrderMenu() method here
    }

    // Stub method for managing payments
    private void managePayments() {
        System.out.println("\n--- Manage Payments ---");
        // Call PaymentController.managePaymentMenu() method here
    }
}
