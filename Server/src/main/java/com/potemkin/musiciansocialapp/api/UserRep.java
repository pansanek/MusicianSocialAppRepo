package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.User;

public interface UserRep extends CrudRepository<User,Integer>{
    
    public User findUserById(Integer id);

}
