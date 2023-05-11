package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.UserRep;
import com.potemkin.musiciansocialapp.models.User;


@RestController
@RequestMapping("/user")
public class UserController {

    UserRep repo;

    @GetMapping("/all-user")
    public List<User> getAll(){
        List<User> user = (List<User>) repo.findAll();
        return user;
    }
    
    @PostMapping("/upload-user")
    public User uploadUser(@RequestBody User user){
        repo.save(user);
        return user;

    }

    @GetMapping("/delete-user")
    public User deleteChild(@RequestParam(name="id") Integer id){
        User user = repo.findUserById(id);
        repo.delete(user);
        return user;
    }
    
}
