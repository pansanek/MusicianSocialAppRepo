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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician_id")
    private Musician musician;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inst_id")
    private Instruments instruments;

    public int getMusicianInstrumentsId() {
        return musicianInstrumentsId;
    }

    public void setMusicianInstrumentsId(int musicianInstrumentsId) {
        this.musicianInstrumentsId = musicianInstrumentsId;
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