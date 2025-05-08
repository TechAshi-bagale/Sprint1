package com.dao;

import com.entity.Artist;
import java.util.List;

/**
 * Artistdao interface defines the data access methods
 * for performing CRUD operations on the Artist entity.
 */
public interface Artistdao {

    /**
     * Saves a new Artist to the database.
     *
     * @param artist The Artist object to be saved.
     */
    void saveArtist(Artist artist);

    /**
     * Retrieves an Artist from the database by its ID.
     *
     * @param id The ID of the Artist to retrieve.
     * @return The Artist object if found, otherwise null.
     */
    Artist getArtistById(int id);

    /**
     * Updates the details of an existing Artist in the database.
     *
     * @param artist The Artist object with updated values.
     */
    void updateArtist(Artist artist);

    /**
     * Deletes an Artist record from the database based on its ID.
     *
     * @param id The ID of the Artist to delete.
     * @return true if the Artist was successfully deleted, false otherwise.
     */
    boolean deleteArtist(int id);

    /**
     * Retrieves a list of all Artist objects from the database.
     *
     * @return A list containing all Artist records.
     */
    List<Artist> getAllArtists();
}
