package com.dao;

import com.entity.Art;
import java.util.List;

/**
 * Artdao interface defines the data access methods
 * for performing CRUD operations on the Art entity.
 */
public interface Artdao {

    /**
     * Adds a new Art entry to the database.
     *
     * @param art The Art object to be added.
     */
    void addArt(Art art);

    /**
     * Updates the details of an existing Art in the database.
     *
     * @param art The Art object with updated values.
     */
    void updateArt(Art art);

    /**
     * Deletes an Art record from the database based on its ID.
     *
     * @param id The ID of the Art to be deleted.
     */
    void deleteArt(int id);

    /**
     * Retrieves an Art object from the database by its ID.
     *
     * @param id The ID of the Art to retrieve.
     * @return The Art object if found, otherwise null.
     */
    Art getArtById(int id);

    /**
     * Retrieves a list of all Art objects from the database.
     *
     * @return A list containing all Art records.
     */
    List<Art> getAllArts();
}
