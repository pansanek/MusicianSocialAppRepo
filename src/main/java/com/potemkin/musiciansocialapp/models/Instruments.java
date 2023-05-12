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
    private int instId;

    @Column(name="inst_name")
    private String instName;

    public int getInstId() {
        return instId;
    }

    public void setInstId(int instId) {
        this.instId = instId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    
}