package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.MusicianGenresRep;
import com.potemkin.musiciansocialapp.models.MusicianGenres;


@RestController
@RequestMapping("/musician_genres")
public class MusicianGenresController {

    @Autowired
    MusicianGenresRep repo;
    
    @GetMapping("/all-musician_genres")
    public List<MusicianGenres> getAllMusicianGenres(){
        List<MusicianGenres> musicianGenres = (List<MusicianGenres>) repo.findAll();
        return musicianGenres;
    }
    
    @PostMapping("/create-musician_genre")
    public MusicianGenres createMusicianGenre(@RequestBody MusicianGenres musicianGenre){
        repo.save(musicianGenre);
        return musicianGenre;
    }
    
    @GetMapping("/delete-musician_genre")
    public MusicianGenres deleteMusician(@RequestParam(name="musician_genres_id") Integer id){
        MusicianGenres musicianGenre = repo.findMusicianGenresByMusicianGenresId(id);
        repo.delete(musicianGenre);
        return musicianGenre;
    }
    
}
