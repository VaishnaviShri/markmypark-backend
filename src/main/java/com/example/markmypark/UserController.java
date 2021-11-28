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
        return userRepository.findAll();
    }

    @PostMapping("/addFirebaseUser")
    public String postExample(@RequestBody User user) throws InterruptedException,  ExecutionException {
       //return "post ka message";
        return firebaseService.saveUserDetails(user);
    }
    
   /* @PostMapping("/postUser")
    public String postBody(@RequestBody String fullName) {
        return "Hello " + fullName;
    }

    @PostMapping(
            value = "/postUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> postBody(@RequestBody User user) {
        User persistedUser = userRepository.save(user);
        return ResponseEntity
                .created(URI
                        .create(String.format("/users/%s", user.firstName)))
                .body(persistedUser);
    }*/

    @PostMapping("/add")
    public User newUser(@RequestBody User newUser){
       return userRepository.save(newUser);
   }

    /*
    @RequestParam("users/searchName") String lastname)
    public List<User> searchByName(){
        return userRepository.findByLastName(lastname);
    }
    @RequestParam("lastName") String lastName, User user){
        model.addAttribute("ISBN", ISBN);
        return "bookDetails";
    }*/


}
