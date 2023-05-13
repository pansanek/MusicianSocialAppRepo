package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.MusicianBandsRep;
import com.potemkin.musiciansocialapp.models.MusicianBands;


@RestController
@RequestMapping("/musician_bands")
public class MusicianBandsController {

    @Autowired
    MusicianBandsRep repo;
    
    @GetMapping("/all-musician_bands")
    public List<MusicianBands> getAllMusicianBands(){
        List<MusicianBands> musicianBands = (List<MusicianBands>) repo.findAll();
        return musicianBands;
    }
    
    @PostMapping("/create-musician_band")
    public MusicianBands createMusicianBand(@RequestBody MusicianBands musicianBand){
        repo.save(musicianBand);
        return musicianBand;
    }
    
    @GetMapping("/delete-musician_band")
    public MusicianBands deleteMusician(@RequestParam(name="musician_bands_id") Integer id){
        MusicianBands musicianBand = repo.findMusicianBandsByMusicianBandsId(id);
        repo.delete(musicianBand);
        return musicianBand;
    }
    
}
