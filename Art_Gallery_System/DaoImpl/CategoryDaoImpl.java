package com.daoimpl;


import com.dao.Categorydao;
import com.entity.Category;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDaoImpl implements Categorydao {

    @Override
    public void addCategory(Category category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
            System.out.println("✅ Category added successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to add category: " + e.getMessage());
        }
    }

    @Override
    public void updateCategory(Category category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(category);
            tx.commit();
            System.out.println("✅ Category updated successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to update category: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                tx = session.beginTransaction();
                session.delete(category);
                tx.commit();
                System.out.println("✅ Category deleted.");
            } else {
                System.out.println("❌ Category not found.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Failed to delete category: " + e.getMessage());
        }
		return false;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, categoryId);
        } catch (Exception e) {
            System.out.println("❌ Error fetching category: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Category> query = session.createQuery("from Category", Category.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("❌ Error fetching categories: " + e.getMessage());
            return null;
        }
    }
}
