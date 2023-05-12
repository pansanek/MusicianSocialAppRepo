package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.RepBase;

public interface RepBaseRep extends CrudRepository<RepBase,Integer>{
    
    public RepBase findRepBaseByRepBaseId(Integer repBaseId);

}
