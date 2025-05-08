package com.service;

import com.entity.Payment;

import java.util.List;

public interface PaymentService {

    // Process a new payment
    void createPayment(Payment payment);

    // Update a payment
    void updatePayment(Payment payment);

    // Delete a payment by ID
    void deletePayment(int paymentId);

    // Get payment by ID
    Payment getPaymentById(int paymentId);

    // Get all payments
    List<Payment> getAllPayments();

    // Get payments by customer ID
    List<Payment> getPaymentsByCustomerId(int customerId);

	
}
