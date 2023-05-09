package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.Band;

public interface BandRep extends CassandraRepository<Band,Integer>{
    
    @AllowFiltering
    public Band findBandById(Integer id);

}
