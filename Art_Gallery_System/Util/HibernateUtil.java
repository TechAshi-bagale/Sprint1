package com.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.entity.*;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Load configuration from hibernate.cfg.xml
            configuration.configure("hibernate.cfg.xml");

            // Add annotated entity classes
            configuration.addAnnotatedClass(Admin.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Artist.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Art.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Payment.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
