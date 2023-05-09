package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.RepBase;

public interface RepBaseRep extends CassandraRepository<RepBase,Integer>{
    
    @AllowFiltering
    public RepBase findRepBaseById(Integer id);

}
