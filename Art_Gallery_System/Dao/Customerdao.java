package com.dao;

import com.entity.Customer;
import java.util.List;

/**
 * Customerdao interface defines the data access methods
 * for performing CRUD operations on the Customer entity.
 */
public interface Customerdao {

    /**
     * Registers a new Customer in the system by saving their details to the database.
     *
     * @param customer The Customer object containing the details to be saved.
     */
    void registerCustomer(Customer customer);

    /**
     * Authenticates a Customer based on their username and password.
     *
     * @param username The username of the Customer.
     * @param password The password of the Customer.
     * @return The Customer object if login is successful, null otherwise.
     */
    Customer loginCustomer(String username, String password);

    /**
     * Retrieves a Customer from the database by their ID.
     *
     * @param id The ID of the Customer to retrieve.
     * @return The Customer object if found, otherwise null.
     */
    Customer getCustomerById(int id);

    /**
     * Retrieves all Customers from the database.
     *
     * @return A list containing all Customer records.
     */
    List<Customer> getAllCustomers();

    /**
     * Updates the details of an existing Customer in the database.
     *
     * @param customer The Customer object with updated information.
     * @return true if the Customer was successfully updated, false otherwise.
     */
    boolean updateCustomer(Customer customer);

    /**
     * Deletes a Customer from the database based on their ID.
     *
     * @param id The ID of the Customer to delete.
     * @return true if the Customer was successfully deleted, false otherwise.
     */
    boolean deleteCustomer(int id);
}
