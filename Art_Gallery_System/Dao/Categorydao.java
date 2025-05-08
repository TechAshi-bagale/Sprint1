package com.dao;

import com.entity.Category;
import java.util.List;

/**
 * Categorydao interface defines the data access methods
 * for performing CRUD operations on the Category entity.
 */
public interface Categorydao {

    /**
     * Adds a new Category to the database.
     *
     * @param category The Category object to be added.
     */
    void addCategory(Category category);

    /**
     * Updates the details of an existing Category in the database.
     *
     * @param category The Category object with updated values.
     */
    void updateCategory(Category category);

    /**
     * Deletes a Category record from the database based on its ID.
     *
     * @param categoryId The ID of the Category to delete.
     * @return true if the Category was successfully deleted, false otherwise.
     */
    boolean deleteCategory(int categoryId);

    /**
     * Retrieves a Category from the database by its ID.
     *
     * @param categoryId The ID of the Category to retrieve.
     * @return The Category object if found, otherwise null.
     */
    Category getCategoryById(int categoryId);

    /**
     * Retrieves a list of all Category objects from the database.
     *
     * @return A list containing all Category records.
     */
    List<Category> getAllCategories();
}
