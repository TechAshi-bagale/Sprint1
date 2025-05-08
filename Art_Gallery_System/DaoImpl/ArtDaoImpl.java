package com.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dao.Artdao;
import com.entity.Art;
import com.util.HibernateUtil;

public class ArtDaoImpl implements Artdao {

    /**
     * Adds a new Art entity to the database.
     * 
     * @param art The Art object to be added.
     */
    @Override
    public void addArt(Art art) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new transaction
            tx = session.beginTransaction();
            
            // Save the Art object to the database
            session.save(art);
            
            // Commit the transaction to persist changes
            tx.commit();
            //System.out.println("Art added successfully.");
        } catch (Exception e) {
            // Rollback if there is an error in the transaction
            if (tx != null && tx.isActive()) {
                tx.rollback(); // rollback only if transaction is active
            }
            // Log the error
            System.err.println("Error while adding art: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing Art entity in the database.
     * 
     * @param art The Art object with updated details.
     */
    @Override
    public void updateArt(Art art) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new transaction
            tx = session.beginTransaction();
            
            // Update the Art object in the database
            session.update(art);
            
            // Commit the transaction to persist changes
            tx.commit();
            System.out.println("Art updated successfully.");
        } catch (Exception e) {
            // Rollback if there is an error in the transaction
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Log the error
            System.err.println("Error while updating art: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deletes an Art entity from the database based on its ID.
     * 
     * @param artId The ID of the Art object to be deleted.
     */
    @Override
    public void deleteArt(int artId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new transaction
            tx = session.beginTransaction();
            
            // Retrieve the Art object from the database by its ID
            Art art = session.get(Art.class, artId);
            if (art != null) {
                // Delete the Art object if found
                session.delete(art);
                System.out.println("Art deleted successfully.");
            } else {
                System.out.println("Art not found with ID: " + artId);
            }
            
            // Commit the transaction to persist the deletion
            tx.commit();
        } catch (Exception e) {
            // Rollback if there is an error in the transaction
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Log the error
            System.err.println("Error while deleting art: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an Art entity from the database based on its ID.
     * 
     * @param artId The ID of the Art object to be retrieved.
     * @return The Art object if found, otherwise null.
     */
    @Override
    public Art getArtById(int artId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve and return the Art object by its ID
            return session.get(Art.class, artId);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error while retrieving art: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all Art entities from the database.
     * 
     * @return A list of all Art objects in the database.
     */
    @Override
    public List<Art> getAllArts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Use HQL to fetch all Art objects from the database
            return session.createQuery("from Art", Art.class).list();
        } catch (Exception e) {
            // Log the error
            System.err.println("Error while fetching art list: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if there is an error
        }
    }
}
