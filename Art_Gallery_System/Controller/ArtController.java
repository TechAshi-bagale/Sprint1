package com.controller;

import java.util.List;
import java.util.Scanner;

import com.entity.Art;
import com.entity.Artist;
import com.entity.Category;
import com.service.ArtService;
import com.serviceimpl.ArtServiceImpl;
import com.service.ArtistService;
import com.service.CategoryService;
import com.serviceimpl.ArtistServiceImpl;
import com.serviceimpl.CategoryServiceImpl;

public class ArtController {

    // Creating instances of services to handle operations related to Art, Artist, and Category
    private static ArtService artService = new ArtServiceImpl();
    private static ArtistService artistService = new ArtistServiceImpl();
    private static CategoryService categoryService = new CategoryServiceImpl();
    private static Scanner sc = new Scanner(System.in); // Scanner to take input from the user

    // Menu to manage Art operations
    public static void manageArtMenu() {
        int choice;
        do {
            // Display menu options for managing art
            System.out.println("\n===== Art Management Menu =====");
            System.out.println("1. Add Art");
            System.out.println("2. Update Art");
            System.out.println("3. Delete Art");
            System.out.println("4. View Art by ID");
            System.out.println("5. View All Arts");
            System.out.println("6. Back to Admin Panel");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); // Take user input for the menu choice

            // Switch statement to handle the selected operation
            switch (choice) {
                case 1:
                    addArt(); // Call method to add art
                    break;
                case 2:
                    updateArt(); // Call method to update art
                    break;
                case 3:
                    deleteArt(); // Call method to delete art
                    break;
                case 4:
                    viewArtById(); // Call method to view art by ID
                    break;
                case 5:
                    viewAllArts(); // Call method to view all arts
                    break;
                case 6:
                    System.out.println("Returning to Admin Panel..."); // Exit the menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid choice
            }
        } while (choice != 6); // Continue until the user chooses to return to the Admin Panel
    }

    // Method to add new art
    public static void addArt() {
        sc.nextLine(); // Consume newline character
        System.out.print("Enter Art Title: ");
        String title = sc.nextLine(); // Take input for Art title
        System.out.print("Enter Art Description: ");
        String description = sc.nextLine(); // Take input for Art description
        System.out.print("Enter Art Price: ");
        double price = sc.nextDouble(); // Take input for Art price

        // Get Artist details by ID
        System.out.print("Enter Artist ID: ");
        int artistId = sc.nextInt();
        Artist artist = artistService.getArtistById(artistId);
        if (artist == null) {
            System.out.println("Invalid Artist ID."); // Handle invalid Artist ID
            return;
        }

        // Get Category details by ID
        System.out.print("Enter Category ID: ");
        int categoryId = sc.nextInt();
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            System.out.println("Invalid Category ID."); // Handle invalid Category ID
            return;
        }

        // Create and set properties for the new Art object
        Art art = new Art();
        art.setTitle(title);
        art.setDescription(description);
        art.setPrice(price);
        art.setArtist(artist);
        art.setCategory(category);

        // Call service to add the art
        artService.addArt(art);
        System.out.println("Art added successfully."); // Success message
    }

    // Method to update an existing art
    public static void updateArt() {
        System.out.print("Enter Art ID to update: ");
        int id = sc.nextInt(); // Take input for the Art ID
        sc.nextLine(); // Consume newline character

        // Fetch the existing Art object
        Art existingArt = artService.getArtById(id);
        if (existingArt != null) {
            // If the art exists, prompt for new values to update
            System.out.print("Enter new title: ");
            String title = sc.nextLine();
            System.out.print("Enter new description: ");
            String description = sc.nextLine();
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();

            // Get new Artist and Category details
            System.out.print("Enter new Artist ID: ");
            int artistId = sc.nextInt();
            Artist artist = artistService.getArtistById(artistId);
            if (artist == null) {
                System.out.println("Invalid Artist ID.");
                return;
            }

            System.out.print("Enter new Category ID: ");
            int categoryId = sc.nextInt();
            Category category = categoryService.getCategoryById(categoryId);
            if (category == null) {
                System.out.println("Invalid Category ID.");
                return;
            }

            // Update the Art object with the new details
            existingArt.setTitle(title);
            existingArt.setDescription(description);
            existingArt.setPrice(price);
            existingArt.setArtist(artist);
            existingArt.setCategory(category);

            // Call service to update the art
            artService.updateArt(existingArt);
            System.out.println("Art updated successfully."); // Success message
        } else {
            System.out.println("Art not found with ID: " + id); // If art not found
        }
    }

    // Method to delete an existing art
    public static void deleteArt() {
        System.out.print("Enter Art ID to delete: ");
        int id = sc.nextInt(); // Take input for the Art ID
        artService.deleteArt(id); // Call service to delete the art
        System.out.println("Art deleted successfully."); // Success message
    }

    // Method to view an art by its ID
    public static void viewArtById() {
        System.out.print("Enter Art ID: ");
        int id = sc.nextInt(); // Take input for the Art ID
        Art art = artService.getArtById(id); // Fetch art by ID
        if (art != null) {
            // If the art exists, display its details
            System.out.println("ID: " + art.getId());
            System.out.println("Title: " + art.getTitle());
            System.out.println("Description: " + art.getDescription());
            System.out.println("Price: ₹" + art.getPrice());
            System.out.println("Artist: " + art.getArtist().getArtistName());
            System.out.println("Category: " + art.getCategory().getCategoryName());
        } else {
            System.out.println("Art not found with ID: " + id); // If art not found
        }
    }

    // Method to view all arts in the system
    public static void viewAllArts() {
        List<Art> artList = artService.getAllArts(); // Fetch all arts
        if (artList.isEmpty()) {
            System.out.println("No artworks found."); // If no art exists
        } else {
            System.out.println("\nAll Arts:");
            // Iterate through the list of arts and display their details
            for (Art art : artList) {
                System.out.println("ID: " + art.getId() +
                        ", Title: " + art.getTitle() +
                        ", Description: " + art.getDescription() +
                        ", Price: ₹" + art.getPrice() +
                        ", Artist: " + art.getArtist().getArtistName() +
                        ", Category: " + art.getCategory().getCategoryName());
            }
        }
    }
}
