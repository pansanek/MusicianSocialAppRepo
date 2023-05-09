package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.RepAdm;

public interface RepAdmRep extends CassandraRepository<RepAdm,Integer>{
    
    @AllowFiltering
    public RepAdm findRepAdmById(Integer id);

}
