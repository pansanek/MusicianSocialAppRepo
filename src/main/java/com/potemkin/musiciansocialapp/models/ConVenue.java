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
@Table(name="con_ven")
public class ConVenue {
    @Id
    @Column(name="con_ven_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conVenId;

    @Column(name="con_ven_name")
    private String conVenName;

    @Column(name="con_ven_address")
    private String conVenAddress;

    @Column(name="con_ven_about")
    private String conVenAbout;
    
    @Column(name="con_adm_id")
    private int conAdmId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "con_adm")
    private ConAdm conAdm;

    public int getConVenId() {
        return conVenId;
    }

    public void setConVenId(int conVenId) {
        this.conVenId = conVenId;
    }

    public String getConVenName() {
        return conVenName;
    }

    public void setConVenName(String conVenName) {
        this.conVenName = conVenName;
    }

    public String getConVenAddress() {
        return conVenAddress;
    }

    public void setConVenAddress(String conVenAddress) {
        this.conVenAddress = conVenAddress;
    }

    public String getConVenAbout() {
        return conVenAbout;
    }

    public void setConVenAbout(String conVenAbout) {
        this.conVenAbout = conVenAbout;
    }

    public int getConAdmId() {
        return conAdmId;
    }

    public void setConAdmId(int conAdmId) {
        this.conAdmId = conAdmId;
    }

    public ConAdm getConAdm() {
        return conAdm;
    }

    public void setConAdm(ConAdm conAdm) {
        this.conAdm = conAdm;
    }

    

}