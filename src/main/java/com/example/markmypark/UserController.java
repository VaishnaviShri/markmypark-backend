package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
   FirebaseService firebaseService;

    @RequestMapping("/sayhi")
    public String sayHi(){
        return "Hi!";
    }

    @RequestMapping("/getall")
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

   /* @PostMapping("/addFirebaseUser")
    public String postExample(@RequestBody User user) throws InterruptedException,  ExecutionException {
       //return "post ka message";
        return firebaseService.saveUserDetails(user);
    }*/

    @PostMapping("/add")
    public User newUser(@RequestBody User newUser){
       return userRepository.save(newUser).block();
   }

}
