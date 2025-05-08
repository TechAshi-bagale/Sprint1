package com.daoimpl;

import com.dao.Admindao;
import com.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;

import javax.persistence.NoResultException;

public class AdminDaoImpl implements Admindao {

    /**
     * Saves the Admin entity to the database.
     * 
     * @param admin The Admin object containing the details to be saved.
     */
    @Override
    public void saveAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new transaction
            transaction = session.beginTransaction();
            
            // Save the admin entity to the database
            session.save(admin);
            
            // Commit the transaction to save changes
            transaction.commit();
        } catch (Exception e) {
            // Rollback in case of an exception to maintain data integrity
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an Admin entity from the database based on the username.
     * 
     * @param username The username of the Admin to be retrieved.
     * @return The Admin object if found, otherwise null.
     */
    @Override
    public Admin getAdminByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Use HQL (Hibernate Query Language) to fetch Admin by username
            return session.createQuery("FROM Admin WHERE username = :username", Admin.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Return null if no result is found for the given username
            return null;
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            return null;
        }
    }
}
