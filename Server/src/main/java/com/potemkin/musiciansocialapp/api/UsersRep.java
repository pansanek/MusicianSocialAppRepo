package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.Users;

public interface UsersRep extends CrudRepository<Users,Integer>{
    
    public Users findUsersByUsersId(Integer usersId);

}
