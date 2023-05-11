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
@Table(name="musician_instruments")
public class MusicianInstruments {
    @Id
    @Column(name="musician_instruments_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musicianInstrumentsId;

    @Column(name="musician_id")
    private int musicianId;

    @Column(name="inst_id")
    private int instId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician")
    private Musician musician;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instruments")
    private Instruments instruments;

    public int getMusicianInstrumentsId() {
        return musicianInstrumentsId;
    }

    public void setMusicianInstrumentsId(int musicianInstrumentsId) {
        this.musicianInstrumentsId = musicianInstrumentsId;
    }

    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public int getInstId() {
        return instId;
    }

    public void setInstId(int instId) {
        this.instId = instId;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Instruments getInstruments() {
        return instruments;
    }

    public void setInstruments(Instruments instruments) {
        this.instruments = instruments;
    }


   

   
}