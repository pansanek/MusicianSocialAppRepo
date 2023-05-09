package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.ConAdm;

public interface ConAdmRep extends CassandraRepository<ConAdm,Integer>{
    
    @AllowFiltering
    public ConAdm findConAdmById(Integer id);

}
