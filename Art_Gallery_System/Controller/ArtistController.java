package com.controller;

import com.entity.Artist;
import com.service.ArtistService;
import com.serviceimpl.ArtistServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ArtistController {

    // Scanner for user input
    private static Scanner sc = new Scanner(System.in);

    // ArtistService object to interact with the service layer
    private static ArtistService artistService = new ArtistServiceImpl();

    // Displays the Artist management menu and handles user choices
    public static void manageArtistMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Manage Artists =====");
            System.out.println("1. Add New Artist");
            System.out.println("2. Update Existing Artist");
            System.out.println("3. Delete Artist");
            System.out.println("4. View All Artists");
            System.out.println("5. Back to Admin Panel");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            int choice = 0;

            // Handle invalid number input
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                continue;
            }

            // Switch-case to route the user's choice
            switch (choice) {
                case 1:
                    addArtist(); // Add a new artist
                    break;
                case 2:
                    updateArtist(); // Update existing artist
                    break;
                case 3:
                    deleteArtist(); // Delete artist by ID
                    break;
                case 4:
                    viewAllArtists(); // Display all artists
                    break;
                case 5:
                    running = false; // Exit to Admin Panel
                    System.out.println("Returning to Admin Panel...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1-5.");
            }
        }
    }

    // Adds a new artist by taking input from the user
    private static void addArtist() {
        System.out.print("Enter Artist Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Bio: ");
        String bio = sc.nextLine();

        // Create new Artist object with ID as 0 (to be auto-generated)
        Artist artist = new Artist(0, name, bio);

        // Call service to add artist
        artistService.addArtist(artist);
        System.out.println("Artist added successfully!");
    }

    // Updates an existing artist's details
    private static void updateArtist() {
        System.out.print("Enter Artist ID to Update: ");
        int id = Integer.parseInt(sc.nextLine());

        // Fetch existing artist by ID
        Artist existingArtist = artistService.getArtistById(id);
        if (existingArtist == null) {
            System.out.println("Artist not found with ID: " + id);
            return;
        }

        // Prompt for new details, allowing skipping fields
        System.out.print("Enter New Artist Name (leave blank to keep unchanged): ");
        String newName = sc.nextLine();

        System.out.print("Enter New Artist Bio (leave blank to keep unchanged): ");
        String newBio = sc.nextLine();

        // Update only if new values are provided
        if (!newName.isEmpty()) {
            existingArtist.setArtistName(newName);
        }
        if (!newBio.isEmpty()) {
            existingArtist.setBio(newBio);
        }

        // Call service to update artist
        artistService.updateArtist(existingArtist);
        System.out.println("Artist updated successfully!");
    }

    // Deletes an artist by their ID
    private static void deleteArtist() {
        System.out.print("Enter Artist ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine());

        // Call service to delete artist
        boolean success = artistService.deleteArtist(id);
        if (success) {
            System.out.println("Artist deleted successfully!");
        } else {
            System.out.println("Artist not found or could not be deleted.");
        }
    }

    // Displays all artists in the system
    private static void viewAllArtists() {
        List<Artist> artists = artistService.getAllArtists();

        // Check if artist list is empty
        if (artists.isEmpty()) {
            System.out.println("No artists found.");
        } else {
            System.out.println("\n----- List of Artists -----");
            for (Artist artist : artists) {
                System.out.println("ID: " + artist.getId());
                System.out.println("Name: " + artist.getArtistName());
                System.out.println("Bio: " + artist.getBio());
                System.out.println("---------------------------");
            }
        }
    }
}
