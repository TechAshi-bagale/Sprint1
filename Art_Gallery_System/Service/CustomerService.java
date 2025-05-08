package com.service;

import com.entity.Customer;
import java.util.List;

public interface CustomerService {
    void registerCustomer(Customer customer);
    Customer loginCustomer(String username, String password);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
}
