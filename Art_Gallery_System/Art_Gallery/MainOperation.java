package com.Online_Art_Gallery;
import static com.Online_Art_Gallery.AllOperation.*; // Importing all operations (for use in other methods)

import javax.swing.*; // Importing Swing library for GUI elements

import com.controller.ArtController;
import com.controller.CustomerController;
import com.entity.Customer;
import com.util.InputUtil;

import java.awt.*; // Importing AWT library for graphical components
import java.util.Scanner; // Importing Scanner for user input

public class MainOperation {

    // Dummy credentials for admin login
    private static final String ADMIN_USERNAME = "Asavari"; // Admin username
    private static final String ADMIN_PASSWORD = "asavari123"; // Admin password

    // Admin login function
    public static boolean adminLogin() {
        JTextField usernameField = new JTextField(); // Creating text field for username input
        JPasswordField passwordField = new JPasswordField(); // Creating password field for password input

        Object[] message = { // Message to show in the login dialog
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION); // Show login dialog

        if (option == JOptionPane.OK_OPTION) { // If OK is clicked
            String username = usernameField.getText(); // Get username from the text field
            String password = new String(passwordField.getPassword()); // Get password from the password field

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) { // Check if credentials match
                JOptionPane.showMessageDialog(null, "✅ Login Successful!"); // Show success message
                return true; // Return true for successful login
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid Credentials!"); // Show error message for invalid credentials
                return false; // Return false for failed login
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login Cancelled."); // If user cancels login
            return false; // Return false for cancelled login
        }
    }

    // Main dashboard after successful login
    public static void dashboard() {
        while (true) { // Infinite loop for the dashboard menu
            System.out.println("\n==========🏥 Art Gallery Management System Dashboard 🏥==========");
            System.out.println("\n==== Welcome to Online Art Gallery ====");
            System.out.println("1. Admin View");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Guest View");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: "); // Display menu options

            Scanner sc = new Scanner(System.in); // Create a scanner for user input
            if (!sc.hasNextInt()) { // Check if the input is a number
                System.out.println("❌ Invalid input! Please enter a number between 1-5."); // Show error if input is not a number
                sc.next(); // Consume invalid input
                continue; // Restart the loop to ask for valid input
            }

            int input = sc.nextInt(); // Get the user's choice
            sc.nextLine(); // Consume leftover newline

            switch (input) { // Switch case based on user's choice
                case 1:
                	AllOperation.adminPanel();
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
                	 System.out.println("Thank you for visiting!");
                     break;
                case 6:
                    System.out.println("🔓 Logging out..."); // Logout message
                    return; // Exit the loop and log out
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-10."); // Error message for invalid choice
            }

            System.out.println("=================================================================");
        }
    }

    public static void main(String[] args) {
        System.out.println("🏥 Welcome to Online Art Gallery Management System 🏥"); // Display welcome message

        boolean isLoggedIn = false; // Flag to track login status

        // Allow retry for login
        for (int attempts = 0; attempts < 3; attempts++) { // Allow up to 3 attempts
            if (adminLogin()) { // If login is successful
                isLoggedIn = true; // Set login status to true
                break; // Exit the loop if login is successful
            } else {
                System.out.println("🔁 Try again (" + (2 - attempts) + " attempts left)\n"); // Display retry message
            }
        }

        if (isLoggedIn) { // If login is successful
            dashboard(); // Show the dashboard
            System.out.println("👋 Thank you for using the Hospital Management System!"); // Display thank you message
        } else {
            System.out.println("🚫 Maximum login attempts exceeded. Exiting..."); // Display exit message if login fails
        }
    }
}
