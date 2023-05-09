package com.potemkin.musiciansocialapp.models;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("con_adm")
public class ConAdm {
    @PrimaryKey
    private int id;

    @Column("user_id")
    private int userId;

    @Column("concert_venue_id")
    private int concertVenueId;

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

    public int getConcertVenueId() {
        return concertVenueId;
    }

    public void setConcertVenueId(int concertVenueId) {
        this.concertVenueId = concertVenueId;
    }
}