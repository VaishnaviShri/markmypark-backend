package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService = new UserService();

    @RequestMapping("/sayhi")
    public String sayHi(){
        return "Hi!";
    }

    @PostMapping("/add")
    public String postExample(@RequestBody User user) throws InterruptedException,  ExecutionException {
        //return "post ka message";
        return userService.saveUserDetails(user);
    }
    @GetMapping("/getall")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }
    @GetMapping("/getUser")
    public User getUser(@RequestParam String uid) throws ExecutionException, InterruptedException {
        return userService.getUser(uid);
    }
    @PostMapping("/addbooking")
    public void booker(@RequestParam String userID,
                       @RequestParam String parkingSlotID,
                       @RequestParam String dt,
                       @RequestParam String checkin,
                       @RequestParam String checkout,
                       @RequestParam Integer refno)
            throws ExecutionException, InterruptedException {

        ParkingSlotService parkingSlotService = new ParkingSlotService();
        int pSlotBookStatus = parkingSlotService.addParkingSlotBooking(userID, parkingSlotID, dt, Integer.parseInt(checkin), Integer.parseInt(checkout));

        if(pSlotBookStatus == 1)
            userService.addUserBooking(userID, parkingSlotID, dt, Integer.parseInt(checkin), Integer.parseInt(checkout), refno);

        userService.promocode_check(userID);
    }

    @PutMapping("/addMoney")
    public void addMoney(@RequestParam double amount, @RequestParam String userID) throws ExecutionException, InterruptedException {
        User u_obj = userService.getUser(userID);
        u_obj.updateWallet(amount);
        userService.saveUserDetails(u_obj);
    }

    @RequestMapping("/updateuserdetails")
    public String updateUserDetails(
            @RequestParam String uid,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String mob_no,
            @RequestParam String address

    ){
        return updateUserDetails(uid, first_name, last_name, mob_no, address);
    }
    @RequestMapping("/updateuser")
    public String updateUser(@RequestBody User updateUser) throws ExecutionException, InterruptedException {
        User oldUser = userService.getUser(updateUser.uid);
        if(!Objects.equals(updateUser.first_name, "") && updateUser.first_name!=null)
            oldUser.first_name = updateUser.first_name;
        if(!Objects.equals(updateUser.last_name, "") && updateUser.last_name!=null )
            oldUser.last_name = updateUser.last_name;
        if(!Objects.equals(updateUser.mob_no, "") && updateUser.mob_no!=null)
            oldUser.mob_no = updateUser.mob_no;
        if(!Objects.equals(updateUser.address, "")&& updateUser.address!=null)
            oldUser.address = updateUser.address;

        return userService.saveUserDetails(oldUser);
    }

    @RequestMapping("/checkout")
    public void check_out(@RequestParam String userID, @RequestParam String parkingSlotID, @RequestParam int refno) throws ExecutionException, InterruptedException {
        userService.checkout(userID, parkingSlotID, refno);
    }
}
