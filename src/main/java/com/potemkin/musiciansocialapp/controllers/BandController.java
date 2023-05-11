package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.BandRep;
import com.potemkin.musiciansocialapp.models.Band;


@RestController
@RequestMapping("/band")
public class BandController {

    BandRep repo;
    
    @GetMapping("/all-bands")
    public List<Band> getAllBands(){
        List<Band> musicians = (List<Band>) repo.findAll();
        return musicians;
    }
    
    @PostMapping("/create-band")
    public Band createBand(@RequestBody Band band){
        repo.save(band);
        return band;
    }
    
    @GetMapping("/delete-band")
    public Band deleteBand(@RequestParam(name="id") Integer id){
        Band band = repo.findBandById(id);
        repo.delete(band);
        return band;
    }
    
}
