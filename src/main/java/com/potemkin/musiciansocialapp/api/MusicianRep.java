package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.Musician;

public interface MusicianRep extends CrudRepository<Musician,Integer>{
    
    public Musician findMusicianById(Integer id);

}
