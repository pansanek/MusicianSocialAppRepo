package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.ConVenueRep;
import com.potemkin.musiciansocialapp.models.ConVenue;


@RestController
@RequestMapping("/con_venue")
public class ConVenueController {

    @Autowired
    ConVenueRep repo;
    
    @GetMapping("/all-con_venue")
    public List<ConVenue> getAllConVenues(){
        List<ConVenue> con_venue = (List<ConVenue>) repo.findAll();
        return con_venue;
    }
    
    @PostMapping("/create-con_venue")
    public ConVenue createConVenue(@RequestBody ConVenue con_venue){
        repo.save(con_venue);
        return con_venue;
    }
    
    @GetMapping("/delete-con_venue")
    public ConVenue deleteConVenue(@RequestParam(name="id") Integer id){
        ConVenue con_venue = repo.findConVenueById(id);
        repo.delete(con_venue);
        return con_venue;
    }
    
}
