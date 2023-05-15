package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.MusicianGenres;

public interface MusicianGenresRep extends CrudRepository<MusicianGenres,Integer>{
    
    public MusicianGenres findMusicianGenresByMusicianGenresId(Integer musicianGenresId);

}
