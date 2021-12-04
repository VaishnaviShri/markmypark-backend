package com.example.markmypark;

import com.example.markmypark.entites.Booking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;


import java.util.ArrayList;
import java.util.List;


@Document(collectionName = "users")
public class User {

    @DocumentId
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNo;
    public double wallet = 0.0;
    public List<Booking> bookingList = new ArrayList<Booking>();

    public User(String id, String firstName, String lastName, String email, String phoneNo, double wallet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.wallet = wallet;
    }

    public void book(Booking b) {
        this.bookingList.add(b);
    }


    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

