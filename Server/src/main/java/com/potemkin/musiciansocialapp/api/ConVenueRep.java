package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.ConVenue;

public interface ConVenueRep extends CrudRepository<ConVenue,Integer>{
    
    public ConVenue findConVenueByConVenId(Integer conVenId);

}
