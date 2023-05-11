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
@Table(name="musician_bands")
public class MusicianBands {
    @Id
    @Column(name="musician_bands_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musicianBandsId;

    @Column(name="musician_id")
    private int musicianId;

    @Column(name="band_id")
    private int bandId;

    @Column(name="genre_id")
    private int genreId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician")
    private Musician musician;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genres")
    private Genres genres;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bands")
    private Band bands;

    public int getMusicianBandsId() {
        return musicianBandsId;
    }

    public void setMusicianBandsId(int musicianBandsId) {
        this.musicianBandsId = musicianBandsId;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
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

    public Band getBands() {
        return bands;
    }

    public void setBands(Band bands) {
        this.bands = bands;
    }

   
}