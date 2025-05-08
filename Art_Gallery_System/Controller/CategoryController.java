package com.controller;

import com.entity.Category;
import com.service.CategoryService;
import com.serviceimpl.CategoryServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CategoryController {

    // Scanner for user input
    private static Scanner sc = new Scanner(System.in);

    // Service object to interact with the business layer
    private static CategoryService categoryService = new CategoryServiceImpl();

    // Main menu for managing categories
    public static void manageCategoryMenu() {
        boolean running = true;

        while (running) {
            // Display menu options
            System.out.println("\n===== Manage Categories =====");
            System.out.println("1. Add New Category");
            System.out.println("2. Update Existing Category");
            System.out.println("3. Delete Category");
            System.out.println("4. View All Categories");
            System.out.println("5. Back to Admin Panel");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            int choice = 0;

            // Validate numeric input
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                continue;
            }

            // Perform action based on user input
            switch (choice) {
                case 1:
                    addCategory(); // Call method to add category
                    break;
                case 2:
                    updateCategory(); // Call method to update category
                    break;
                case 3:
                    deleteCategory(); // Call method to delete category
                    break;
                case 4:
                    viewAllCategories(); // Call method to view all categories
                    break;
                case 5:
                    running = false; // Exit loop to return to Admin Panel
                    System.out.println("Returning to Admin Panel...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1-5.");
            }
        }
    }

    // Method to add a new category
    private static void addCategory() {
        System.out.print("Enter Category Name: ");
        String name = sc.nextLine();

        // Validate that category name is not empty
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return;
        }

        // Create Category object and call service to add
        Category category = new Category(name.trim());
        categoryService.addCategory(category);
        System.out.println("Category added successfully!");
    }

    // Method to update an existing category
    private static void updateCategory() {
        System.out.print("Enter Category ID to Update: ");
        int id = Integer.parseInt(sc.nextLine());

        // Fetch existing category by ID
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory == null) {
            System.out.println("Category not found with ID: " + id);
            return;
        }

        // Prompt for new name
        System.out.print("Enter New Category Name: ");
        String newName = sc.nextLine();

        // Validate that new name is not empty
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Category name cannot be empty. Update aborted.");
            return;
        }

        // Set new name and update category
        existingCategory.setCategoryName(newName.trim());
        categoryService.updateCategory(existingCategory);
        System.out.println("Category updated successfully!");
    }

    // Method to delete a category
    private static void deleteCategory() {
        System.out.print("Enter Category ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine());

        // Call service to delete category and show result
        boolean success = categoryService.deleteCategory(id);
        if (success) {
            System.out.println("Category deleted successfully!");
        } else {
            System.out.println("Category not found or could not be deleted.");
        }
    }

    // Method to display all categories
    private static void viewAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        // Check if list is empty
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
        } else {
            System.out.println("\n----- List of Categories -----");
            for (Category cat : categories) {
                System.out.println("ID: " + cat.getId() + " | Name: " + cat.getCategoryName());
            }
        }
    }
}
