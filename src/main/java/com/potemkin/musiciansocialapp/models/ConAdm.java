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
@Table(name="con_adm")
public class ConAdm {
    @Id
    @Column(name="con_adm_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conAdmid;

    @Column(name="user_id")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    public int getConAdmid() {
        return conAdmid;
    }

    public void setConAdmid(int conAdmid) {
        this.conAdmid = conAdmid;
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