package com.service;
import com.entity.Art;
import java.util.List;

public interface ArtService {

    void addArt(Art art);

    void updateArt(Art art);

    void deleteArt(int id);

    Art getArtById(int id);

    List<Art> getAllArts();
}
