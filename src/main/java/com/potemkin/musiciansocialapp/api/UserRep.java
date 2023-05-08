package com.potemkin.musiciansocialapp.api;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.potemkin.musiciansocialapp.models.User;

public interface UserRep extends CassandraRepository<User,Integer>{
    
    @AllowFiltering
    public User findUserById(Integer id);

}
