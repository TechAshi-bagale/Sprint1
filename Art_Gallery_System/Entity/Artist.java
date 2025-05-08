package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "bio", length = 500)
    private String bio;

    // One-to-Many Mapping with Art
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Art> arts;

    public Artist() {
        super();
    }

    public Artist(int id, String artistName, String bio) {
        super();
        this.id = id;
        this.artistName = artistName;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Art> getArts() {
        return arts;
    }

    public void setArts(List<Art> arts) {
        this.arts = arts;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", artistName=" + artistName + ", bio=" + bio + "]";
    }
}
