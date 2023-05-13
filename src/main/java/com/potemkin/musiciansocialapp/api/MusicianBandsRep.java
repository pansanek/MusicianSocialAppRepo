package com.potemkin.musiciansocialapp.api;


import org.springframework.data.repository.CrudRepository;

import com.potemkin.musiciansocialapp.models.MusicianBands;

public interface MusicianBandsRep extends CrudRepository<MusicianBands,Integer>{
    
    public MusicianBands findMusicianBandsByMusicianBandsId(Integer musicianBandsId);

}
