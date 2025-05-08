package com.dao;

import com.entity.Payment;

import java.util.List;

public interface Paymentdao {

    // Save a new payment
    void savePayment(Payment payment);

    // Update an existing payment
    void updatePayment(Payment payment);

    // Delete payment by ID
    void deletePayment(int paymentId);

    // Get payment by ID
    Payment getPaymentById(int paymentId);

    // Get all payments
    List<Payment> getAllPayments();

    // Get payments by customer ID
    List<Payment> getPaymentsByCustomerId(int customerId);
}
