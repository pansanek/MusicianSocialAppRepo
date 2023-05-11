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
@Table(name="rep_adm")
public class RepAdm {
    @Id
    @Column(name="rep_adm_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repAdmid;

    public int getRepAdmid() {
        return repAdmid;
    }

    public void setRepAdmid(int repAdmid) {
        this.repAdmid = repAdmid;
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

    @Column(name="user_id")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

}