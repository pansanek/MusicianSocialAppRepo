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
@Table(name="rep_base")
public class RepBase {
    @Id
    @Column(name="rep_base_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repBaseId;

    @Column(name="rep_base_name")
    private String repBaseName;

    @Column(name="rep_base_address")
    private String repBaseAddress;

    @Column(name="rep_base_about")
    private String repBaseAbout;
    
    @Column(name="rep_adm_id")
    private int repAdmId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rep_adm")
    private RepAdm repAdm;

    public int getRepBaseId() {
        return repBaseId;
    }

    public void setRepBaseId(int repBaseId) {
        this.repBaseId = repBaseId;
    }

    public String getRepBaseName() {
        return repBaseName;
    }

    public void setRepBaseName(String repBaseName) {
        this.repBaseName = repBaseName;
    }

    public String getRepBaseAddress() {
        return repBaseAddress;
    }

    public void setRepBaseAddress(String repBaseAddress) {
        this.repBaseAddress = repBaseAddress;
    }

    public String getRepBaseAbout() {
        return repBaseAbout;
    }

    public void setRepBaseAbout(String repBaseAbout) {
        this.repBaseAbout = repBaseAbout;
    }

    public int getRepAdmId() {
        return repAdmId;
    }

    public void setRepAdmId(int repAdmId) {
        this.repAdmId = repAdmId;
    }

    public RepAdm getRepAdm() {
        return repAdm;
    }

    public void setRepAdm(RepAdm repAdm) {
        this.repAdm = repAdm;
    }

}