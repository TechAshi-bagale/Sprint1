package com.Online_Art_Gallery;

import com.controller.*;
import com.entity.Customer;
import com.util.InputUtil;

import java.util.Scanner;

public class App {

    private static final String DEFAULT_ADMIN_USERNAME = "Asavari";
    private static final String DEFAULT_ADMIN_PASSWORD = "asavari@123";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==== Welcome to Online Art Gallery ====");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Guest View");
            System.out.println("5. Exit");

            int choice = InputUtil.getValidatedIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username: ");
                    String adminUsername = scanner.nextLine();

                    System.out.print("Enter Admin Password: ");
                    String adminPassword = InputUtil.readHiddenPassword();

                    if (adminUsername.equals(DEFAULT_ADMIN_USERNAME) && adminPassword.equals(DEFAULT_ADMIN_PASSWORD)) {
                        System.out.println("Login successful.");
                        AllOperation.adminPanel();
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 2:
                    Customer loggedInCustomer = CustomerController.loginCustomer();
                    if (loggedInCustomer != null) {
                        AllOperation.customerPanel(loggedInCustomer);
                    }
                    break;

                case 3:
                    CustomerController.registerCustomer();
                    break;

                case 4:
                    System.out.println("\n=== Guest Art View ===");
                    ArtController.viewAllArts();
                    break;

                case 5:
                    exit = true;
                    System.out.println("Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }}