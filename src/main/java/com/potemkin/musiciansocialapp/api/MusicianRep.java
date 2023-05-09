package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.Musician;

public interface MusicianRep extends CassandraRepository<Musician,Integer>{
    
    @AllowFiltering
    public Musician findMusicianById(Integer id);

}
