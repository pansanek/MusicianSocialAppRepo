package com.potemkin.musiciansocialapp.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="musician_genres")
public class MusicianGenres {
    @Id
    @Column(name="musician_genres_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musiciangenresId;

    @Column(name="musician_id")
    private int musicianId;

    @Column(name="genre_id")
    private int genreId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician")
    private Musician musician;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genres")
    private Genres genres;


    public int getMusiciangenresId() {
        return musiciangenresId;
    }

    public void setMusiciangenresId(int musiciangenresId) {
        this.musiciangenresId = musiciangenresId;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public int getgenreId() {
        return genreId;
    }

    public void setgenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }


   
}