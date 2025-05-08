package com.daoimpl;

import com.dao.Artistdao;
import com.entity.Artist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;

import java.util.List;

public class ArtistDaoImpl implements Artistdao {

    @Override
    public void saveArtist(Artist artist) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(artist);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Artist getArtistById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Artist.class, id);
        }
    }

    @Override
    public void updateArtist(Artist artist) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(artist);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteArtist(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                tx = session.beginTransaction();
                session.delete(artist);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Artist> getAllArtists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Artist", Artist.class).list();
        }
    }
}
