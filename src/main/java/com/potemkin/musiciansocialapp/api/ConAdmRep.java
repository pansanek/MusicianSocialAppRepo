package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.ConAdm;

public interface ConAdmRep extends CrudRepository<ConAdm,Integer>{
    
    public ConAdm findConAdmById(Integer id);

}
