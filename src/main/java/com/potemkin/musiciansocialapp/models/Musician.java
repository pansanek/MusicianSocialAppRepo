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
@Table(name="musician")
public class Musician {
    @Id
    @Column(name="musician_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int musicianId;

    @Column(name="user_id")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}