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
    private int repAdmId;

    public int getRepAdmId() {
        return repAdmId;
    }

    public void setRepAdmId(int repAdmId) {
        this.repAdmId = repAdmId;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users users;

}