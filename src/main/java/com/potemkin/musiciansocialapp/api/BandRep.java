package com.potemkin.musiciansocialapp.api;



import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.Band;

public interface BandRep extends CrudRepository<Band,Integer>{
    
    public Band findBandById(Integer id);

}
