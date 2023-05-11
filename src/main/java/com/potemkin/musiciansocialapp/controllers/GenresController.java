package com.potemkin.musiciansocialapp.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.GenresRep;
import com.potemkin.musiciansocialapp.models.Genres;


@RestController
@RequestMapping("/genres")
public class GenresController {

    GenresRep repo;
    
    @GetMapping("/all-genres")
    public List<Genres> getAllGenress(){
        List<Genres> Genress = (List<Genres>) repo.findAll();
        return Genress;
    }
    
    @PostMapping("/create-genre")
    public Genres createGenres(@RequestBody Genres genre){
        repo.save(genre);
        return genre;
    }
    
    @GetMapping("/delete-genre")
    public Genres deleteGenres(@RequestParam(name="id") Integer id){
        Genres genre = repo.findGenresById(id);
        repo.delete(genre);
        return genre;
    }
    
}
