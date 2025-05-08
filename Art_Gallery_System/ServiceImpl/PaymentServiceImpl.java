package com.serviceimpl;

import com.dao.Paymentdao;
import com.daoimpl.PaymentDaoImpl;
import com.entity.Payment;
import com.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private Paymentdao paymentDAO = new PaymentDaoImpl();

    @Override
    public void createPayment(Payment payment) {
        paymentDAO.savePayment(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDAO.updatePayment(payment);
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentDAO.deletePayment(paymentId);
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        return paymentDAO.getPaymentById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    @Override
    public List<Payment> getPaymentsByCustomerId(int customerId) {
        return paymentDAO.getPaymentsByCustomerId(customerId);
    }
}
