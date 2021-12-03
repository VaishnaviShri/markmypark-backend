package com.example.markmypark;

import com.example.markmypark.entites.Booking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;


import java.util.List;


@Document(collectionName = "users")
public class User {

    @DocumentId
    public String id;
    public int typeOfAccount =0; //0 for user, 1 for admin, 2 for worker
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNo;
    public String resAddress;
    public String carNo;
    public double wallet =0.0;
    public List<Booking> bookingList;

    public User(String id, int typeOfAccount, String firstName, String lastName, String email, String phoneNo, String resAddress, String carNo, double wallet) {
        this.id = id;
        this.typeOfAccount = typeOfAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.resAddress = resAddress;
        this.carNo = carNo;
        this.wallet = wallet;
    }

    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

