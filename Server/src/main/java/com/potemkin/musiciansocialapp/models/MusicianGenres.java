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
    private int musicianGenresId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician_id")
    private Musician musician;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genres genres;


    public int getMusiciangenresId() {
        return musicianGenresId;
    }

    public void setMusiciangenresId(int musicianGenresId) {
        this.musicianGenresId = musicianGenresId;
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