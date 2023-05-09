package com.potemkin.musiciansocialapp.models;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("rep_adm")
public class RepAdm {
    @PrimaryKey
    private int id;

    @Column("user_id")
    private int userId;

    @Column("repetision_base_id")
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