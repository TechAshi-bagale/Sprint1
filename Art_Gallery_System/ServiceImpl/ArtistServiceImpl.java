package com.serviceimpl;

import com.dao.Artistdao;
import com.daoimpl.ArtistDaoImpl;
import com.entity.Artist;
import com.service.ArtistService;

import java.util.List;

public class ArtistServiceImpl implements ArtistService {

    private Artistdao artistDAO = new ArtistDaoImpl();

    @Override
    public void addArtist(Artist artist) {
        artistDAO.saveArtist(artist);
    }

    @Override
    public Artist getArtistById(int id) {
        return artistDAO.getArtistById(id);
    }

    @Override
    public void updateArtist(Artist artist) {
        artistDAO.updateArtist(artist);
    }

    @Override
    public boolean deleteArtist(int id) {
        return artistDAO.deleteArtist(id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDAO.getAllArtists();
    }
}
