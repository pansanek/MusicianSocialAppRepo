package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.ConVenue;

public interface ConVenueRep extends CassandraRepository<ConVenue,Integer>{
    
    @AllowFiltering
    public ConVenue findConVenueById(Integer id);

}
