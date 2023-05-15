package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.RepAdmRep;
import com.potemkin.musiciansocialapp.models.RepAdm;


@RestController
@RequestMapping("/rep_adm")
public class RepAdmController {
    
    @Autowired
    RepAdmRep repo;
    
    @GetMapping("/all-rep_adms")
    public List<RepAdm> getAllRepAdms(){
        List<RepAdm> RepAdms = (List<RepAdm>) repo.findAll();
        return RepAdms;
    }
    
    @PostMapping("/create-rep_adm")
    public RepAdm createRepAdm(@RequestBody RepAdm rep_adm){
        repo.save(rep_adm);
        return rep_adm;
    }
    
    @GetMapping("/delete-rep_adm")
    public RepAdm deleteRepAdm(@RequestParam(name="rep_adm_id") Integer id){
        RepAdm rep_adm = repo.findRepAdmByRepAdmId(id);
        repo.delete(rep_adm);
        return rep_adm;
    }
    
}
