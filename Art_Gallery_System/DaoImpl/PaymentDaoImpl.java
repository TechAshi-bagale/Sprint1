package com.daoimpl;

import com.dao.Paymentdao;
import com.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;

import java.util.List;

public class PaymentDaoImpl implements Paymentdao {

    @Override
    public void savePayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
            System.out.println("Payment processed successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
            System.out.println("Payment updated successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Payment payment = session.get(Payment.class, paymentId);
            if (payment != null) {
                transaction = session.beginTransaction();
                session.delete(payment);
                transaction.commit();
                System.out.println("Payment deleted successfully.");
            } else {
                System.out.println("Payment not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Payment.class, paymentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Payment", Payment.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsByCustomerId(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Payment where customer.id = :customerId", Payment.class)
                    .setParameter("customerId", customerId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
