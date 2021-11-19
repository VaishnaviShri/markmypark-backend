package com.example.markmypark.controllers;

import com.example.markmypark.entity.User;
import com.example.markmypark.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/sayhi")
    public String sayHi(){
        return "Hi";
    }

    @RequestMapping("/getall")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }


}
