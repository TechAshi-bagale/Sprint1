package com.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.Orderdao;
import com.entity.Customer;
import com.entity.Order;
import com.util.HibernateUtil;

public class OrderDaoImpl implements Orderdao {

    @Override
    public void saveOrder(Order order) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
            System.out.println("✅ Order saved successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to save order.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(order);
            tx.commit();
            System.out.println("✅ Order updated successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to update order.");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Order order = session.get(Order.class, orderId);
            if (order != null) {
                tx = session.beginTransaction();
                session.delete(order);
                tx.commit();
                System.out.println("✅ Order deleted successfully.");
            } else {
                System.out.println("❌ Order not found with ID: " + orderId);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to delete order.");
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, orderId);
        } catch (Exception e) {
            System.out.println("❌ Failed to fetch order.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        } catch (Exception e) {
            System.out.println("❌ Failed to fetch all orders.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByCustomerId(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order o WHERE o.customer = :customer", Order.class)
                          .setParameter("customer", customer)
                          .list();
        } catch (Exception e) {
            System.out.println("❌ Failed to fetch orders for customer.");
            e.printStackTrace();
            return null;
        }
    }
}
