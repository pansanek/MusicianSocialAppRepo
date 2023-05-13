package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.MusicianInstruments;

public interface MusicianInstrumentsRep extends CrudRepository<MusicianInstruments,Integer>{
    
    public MusicianInstruments findMusicianInstrumentsByMusicianInstrumentsId(Integer musicianInstrumentsId);

}
