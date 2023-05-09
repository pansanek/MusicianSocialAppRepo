package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.RepBaseRep;
import com.potemkin.musiciansocialapp.models.RepBase;


@RestController
@RequestMapping("/rep_base")
public class RepBaseController {

    @Autowired
    RepBaseRep repo;
    
    @GetMapping("/all-rep_bases")
    public List<RepBase> getAllRepBases(){
        List<RepBase> rep_bases = (List<RepBase>) repo.findAll();
        return rep_bases;
    }
    
    @PostMapping("/create-rep_base")
    public RepBase createRepBase(@RequestBody RepBase rep_base){
        repo.save(rep_base);
        return rep_base;
    }
    
    @GetMapping("/delete-rep_base")
    public RepBase deleteRepBase(@RequestParam(name="id") Integer id){
        RepBase rep_base = repo.findRepBaseById(id);
        repo.delete(rep_base);
        return rep_base;
    }
    
}
