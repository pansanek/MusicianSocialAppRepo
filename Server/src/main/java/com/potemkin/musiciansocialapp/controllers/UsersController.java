package com.potemkin.musiciansocialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.musiciansocialapp.api.UsersRep;
import com.potemkin.musiciansocialapp.models.Users;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRep repo;

    @GetMapping("/all-users")
    public List<Users> getAll(){
        List<Users> Users = (List<Users>) repo.findAll();
        return Users;
    }
    
    @PostMapping("/upload-users")
    public Users uploadUsers(@RequestBody Users Users){
        repo.save(Users);
        return Users;

    }

    @GetMapping("/delete-users")
    public Users deleteUsers(@RequestParam(name="users_id") Integer id){
        Users Users = repo.findUsersByUsersId(id);
        repo.delete(Users);
        return Users;
    }

    @PostMapping("/auth-user")
    public String authUser(@RequestBody Users users){
        Users Users = repo.findUsersByEmail(users.getEmail());
        if(Users != null){
            if(Users.getPassword().equals(users.getPassword())){
            return "OK";}
            else return "Bad password";
        }
        return "No such user";
    
    }

    @GetMapping("/user-by-email")
    public Users searchUserByEmail(@RequestParam(name="email") String email){
        Users User = repo.findUsersByEmail(email);
        return User;
    
    }
    
}
