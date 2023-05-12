package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.MusicianRep;
import com.potemkin.musiciansocialapp.models.Musician;


@RestController
@RequestMapping("/musician")
public class MusicianController {

    @Autowired
    MusicianRep repo;
    
    @GetMapping("/all-musicians")
    public List<Musician> getAllMusicians(){
        List<Musician> musicians = (List<Musician>) repo.findAll();
        return musicians;
    }
    
    @PostMapping("/create-musician")
    public Musician createMusician(@RequestBody Musician musician){
        repo.save(musician);
        return musician;
    }
    
    @GetMapping("/delete-musician")
    public Musician deleteMusician(@RequestParam(name="musician_id") Integer id){
        Musician musician = repo.findMusicianByMusicianId(id);
        repo.delete(musician);
        return musician;
    }
    
}
