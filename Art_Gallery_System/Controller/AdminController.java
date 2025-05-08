package com.controller;

import com.service.AdminService;
import com.serviceimpl.AdminServiceImpl;
import java.io.Console;
import java.util.Scanner;

public class AdminController {

    // AdminService instance to access admin-related operations
    private AdminService adminService = new AdminServiceImpl();

    // Scanner for user input
    private Scanner sc = new Scanner(System.in);

    // Method to handle admin login
    public boolean adminLogin() {
        // Prompt admin to enter username
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();

        String password = "";
        Console console = System.console();

        // If system console is available, read password securely (without echoing)
        if (console != null) {
            char[] passwordChars = console.readPassword("Enter Admin Password: ");
            password = new String(passwordChars);
        } else {
            // Fallback for IDEs where Console is not supported — reads password visibly
            System.out.print("Enter Admin Password: ");
            password = sc.nextLine();
        }

        // Validate the admin login credentials
        if (adminService.validateAdminLogin(username, password)) {
            System.out.println("\nLogin Successful! Welcome Admin.");
            return true;
        } else {
            System.out.println("\nInvalid credentials. Please try again.");
            return false;
        }
    }

    // Method to create/register a default admin (called during setup or app start)
    public void createDefaultAdmin() {
        adminService.registerDefaultAdmin();
    }
}
