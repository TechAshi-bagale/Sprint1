package com.service;

import com.entity.Artist;
import java.util.List;

public interface ArtistService {
    void addArtist(Artist artist);
    void updateArtist(Artist artist);
    boolean deleteArtist(int artistId);
    Artist getArtistById(int artistId);
    List<Artist> getAllArtists();
}
