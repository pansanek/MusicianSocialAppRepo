package com.potemkin.musiciansocialapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="instruments")
public class Instruments {
    @Id
    @Column(name="inst_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inst_Id;

    @Column(name="inst_name")
    private String inst_Name;

    public int getInstId() {
        return inst_Id;
    }

    public void setInstId(int inst_Id) {
        this.inst_Id = inst_Id;
    }

    public String getInstName() {
        return inst_Name;
    }

    public void setInstName(String inst_Name) {
        this.inst_Name = inst_Name;
    }

    
}