package com.example.markmypark;

import com.example.markmypark.Booking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;


import java.util.List;


@Document(collectionName = "users")
public class User {

    @DocumentId
    public String id;
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

    public String getName(){
        return firstName.concat(lastName);
    }
    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

