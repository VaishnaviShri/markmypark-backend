package com.example.markmypark.entity;

import com.example.markmypark.entity.Booking;
import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    @Id public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNo;
    public double wallet =0.0;
    public List<Booking> bookingList;

    public User(String firstName, String lastName, String email, String phoneNo, double wallet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.wallet = wallet;
    }




    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

