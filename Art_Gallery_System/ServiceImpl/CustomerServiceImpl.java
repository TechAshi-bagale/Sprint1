package com.serviceimpl;

import com.dao.Customerdao;
import com.daoimpl.CustomerDAOImpl;
import com.entity.Customer;
import com.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private Customerdao customerDAO = new CustomerDAOImpl();

    @Override
    public void registerCustomer(Customer customer) {
        customerDAO.registerCustomer(customer);
    }

    @Override
    public Customer loginCustomer(String username, String password) {
        return customerDAO.loginCustomer(username, password);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }
}
