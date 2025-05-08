package com.serviceimpl;

import java.util.List;
import com.dao.Artdao;
import com.daoimpl.ArtDaoImpl;
import com.entity.Art;
import com.service.ArtService;

public class ArtServiceImpl implements ArtService {

    private Artdao artDao = new ArtDaoImpl();

    @Override
    public void addArt(Art art) {
        artDao.addArt(art);
    }

    @Override
    public void updateArt(Art art) {
        artDao.updateArt(art);
    }

    @Override
    public void deleteArt(int artId) {
        artDao.deleteArt(artId);
    }

    @Override
    public Art getArtById(int artId) {
        return artDao.getArtById(artId);
    }

    @Override
    public List<Art> getAllArts() {
        return artDao.getAllArts();
    }
}
