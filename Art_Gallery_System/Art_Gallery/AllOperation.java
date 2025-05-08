package com.Online_Art_Gallery;

import com.controller.*;
import com.entity.Customer;
import com.util.InputUtil;

import java.util.Scanner;

public class AllOperation {

    private static final Scanner scanner = new Scanner(System.in);

    public static void adminPanel() {
        ArtistController artistController = new ArtistController();
        CategoryController categoryController = new CategoryController();
        ArtController artController = new ArtController();
        CustomerController customerController = new CustomerController();
        OrderController orderController = new OrderController();
        PaymentController paymentController = new PaymentController();

        while (true) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("1. Manage Artists");
            System.out.println("2. Manage Categories");
            System.out.println("3. Manage Arts");
            System.out.println("4. Manage Customers");
            System.out.println("5. Manage Orders");
            System.out.println("6. Manage Payments");
            System.out.println("7. View All Payments");
            System.out.println("8. Exit to Main Menu");

            int choice = InputUtil.getValidatedIntegerInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    artistController.manageArtistMenu();
                    break;
                case 2:
                    categoryController.manageCategoryMenu();
                    break;
                case 3:
                    artController.manageArtMenu();
                    break;
                case 4:
                    customerController.manageCustomers();
                    break;
                case 5:
                    orderController.viewAllOrders();
                    break;
                case 6:
                    paymentController.managePayments(); // If you have a menu for Payment
                    break;
                case 7:
                    PaymentController.viewAllPayments(); // Static method OK if already implemented
                    break;
                case 8:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    public static void customerPanel(Customer customer) {
        ArtController artController = new ArtController();
        OrderController orderController = new OrderController();
        PaymentController paymentController = new PaymentController();

        while (true) {
            System.out.println("\n===== Customer Panel (" + customer.getFullName() + ") =====");
            System.out.println("1. View Available Arts");
            System.out.println("2. Place Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Make Payment");
            System.out.println("5. View My Payments");
            System.out.println("6. Exit to Main Menu");

            int choice = InputUtil.getValidatedIntegerInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    artController.viewAllArts();
                    break;
                case 2:
                    orderController.placeOrder(customer);
                    break;
                case 3:
                    orderController.viewOrdersByCustomer(customer);
                    break;
                case 4:
                    paymentController.makePayment(customer);
                    break;
                case 5:
                    PaymentController.viewCustomerPayments(customer.getId()); // Static method if needed
                    break;
                case 6:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
