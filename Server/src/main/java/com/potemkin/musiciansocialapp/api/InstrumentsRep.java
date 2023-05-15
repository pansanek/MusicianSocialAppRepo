package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.Instruments;

public interface InstrumentsRep extends CrudRepository<Instruments,Integer>{
    
    public Instruments findInstrumentsByInstId(Integer instId);

}
