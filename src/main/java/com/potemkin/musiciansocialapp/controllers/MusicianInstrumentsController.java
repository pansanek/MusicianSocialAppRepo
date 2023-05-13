package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.MusicianInstrumentsRep;
import com.potemkin.musiciansocialapp.models.MusicianInstruments;


@RestController
@RequestMapping("/musician_instruments")
public class MusicianInstrumentsController {

    @Autowired
    MusicianInstrumentsRep repo;
    
    @GetMapping("/all-musician_instruments")
    public List<MusicianInstruments> getAllMusicianInstruments(){
        List<MusicianInstruments> musicianInstruments = (List<MusicianInstruments>) repo.findAll();
        return musicianInstruments;
    }
    
    @PostMapping("/create-musician_instrument")
    public MusicianInstruments createMusicianInstrument(@RequestBody MusicianInstruments musicianInstrument){
        repo.save(musicianInstrument);
        return musicianInstrument;
    }
    
    @GetMapping("/delete-musician_instrument")
    public MusicianInstruments deleteMusician(@RequestParam(name="musician_instruments_id") Integer id){
        MusicianInstruments musicianInstrument = repo.findMusicianInstrumentsByMusicianInstrumentsId(id);
        repo.delete(musicianInstrument);
        return musicianInstrument;
    }
    
}
