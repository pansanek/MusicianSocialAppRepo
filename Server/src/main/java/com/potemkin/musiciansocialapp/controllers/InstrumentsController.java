package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.InstrumentsRep;
import com.potemkin.musiciansocialapp.models.Instruments;


@RestController
@RequestMapping("/instruments")
public class InstrumentsController {
    
    @Autowired
    InstrumentsRep repo;
    
    @GetMapping("/all-instruments")
    public List<Instruments> getAllInstruments(){
        List<Instruments> Instruments = (List<Instruments>) repo.findAll();
        return Instruments;
    }
    
    @PostMapping("/create-instruments")
    public Instruments createInstruments(@RequestBody Instruments instruments){
        repo.save(instruments);
        return instruments;
    }
    
    @GetMapping("/delete-Instruments")
    public Instruments deleteInstruments(@RequestParam(name="inst_id") Integer id){
        Instruments Instruments = repo.findInstrumentsByInstId(id);
        repo.delete(Instruments);
        return Instruments;
    }
    
}
