package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.Genres;

public interface GenresRep extends CrudRepository<Genres,Integer>{
    
    public Genres findGenresById(Integer id);

}
