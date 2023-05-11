package com.potemkin.musiciansocialapp.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.ConAdmRep;
import com.potemkin.musiciansocialapp.models.ConAdm;


@RestController
@RequestMapping("/con_adm")
public class ConAdmController {

    ConAdmRep repo;
    
    @GetMapping("/all-con_adm")
    public List<ConAdm> getAllConAdm(){
        List<ConAdm> conAdms = (List<ConAdm>) repo.findAll();
        return conAdms;
    }
    
    @PostMapping("/create-con_adm")
    public ConAdm createConAdm(@RequestBody ConAdm conAdm){
        repo.save(conAdm);
        return conAdm;
    }
    
    @GetMapping("/delete-con_adm")
    public ConAdm deleteConAdm(@RequestParam(name="id") Integer id){
        ConAdm conAdm = repo.findConAdmById(id);
        repo.delete(conAdm);
        return conAdm;
    }
    
}
