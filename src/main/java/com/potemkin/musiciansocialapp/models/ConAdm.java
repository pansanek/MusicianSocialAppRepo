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
    private int conAdmId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users users;

    public int getConAdmId() {
        return conAdmId;
    }

    public void setConAdmId(int conAdmId) {
        this.conAdmId = conAdmId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
   
}