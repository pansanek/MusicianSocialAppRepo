package com.potemkin.musiciansocialapp.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rep_adm")
public class RepAdm {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="repetision_base_id")
    private int repetitionBaseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRepetitionBaseId() {
        return repetitionBaseId;
    }

    public void setRepetitionBaseId(int repetitionBaseId) {
        this.repetitionBaseId = repetitionBaseId;
    }
}