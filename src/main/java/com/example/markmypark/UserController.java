package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/users")
public class UserController {

    //@Autowired
    //private UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/sayhi")
    public String sayHi(){
        return "Hi!";
    }

    @PostMapping("/add")
    public String postExample(@RequestBody User user) throws InterruptedException,  ExecutionException {
        //return "post ka message";
        return userService.saveUserDetails(user);
    }
    @RequestMapping("/getall")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }
    @RequestMapping("/getUser")
    public User getUser(@RequestParam String uid) throws ExecutionException, InterruptedException {
        return userService.getUser(uid);
    }
    @RequestMapping("/addbooking")
    public void booker(@RequestParam String userID,
                       @RequestParam String parkingSlotID,
                       @RequestParam String dt,
                       @RequestParam int checkin,
                       @RequestParam int checkout,
                       @RequestParam Integer refno)
            throws ExecutionException, InterruptedException {

        ParkingSlotService parkingSlotService = new ParkingSlotService();
        int pSlotBookStatus = parkingSlotService.addParkingSlotBooking(userID, parkingSlotID, dt, checkin, checkout);

        if(pSlotBookStatus == 1)
            userService.addUserBooking(userID, parkingSlotID, checkin, checkout, refno);
    }


    /*



    @PostMapping("/add")
    public User newUser(@RequestBody User newUser){
       return userRepository.save(newUser).block();
   }
*/
}
