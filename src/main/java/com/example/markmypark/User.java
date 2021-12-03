package com.example.markmypark;

import com.example.markmypark.entites.Booking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;


import java.util.List;


@Document(collectionName = "users")
public class User {

    @DocumentId
    public String uid;
    public String email;
    public String displayName;
    public String photoURL;
    public Boolean emailVerified;
    public String first_name;
    public String last_name;
    public String user_name;
    public String address;
    public String mob_no;
    public String car_reg_no;
    public String userType;
    public double wallet =0.0;
    public List<Booking> bookingList;

    public User(String uid, String email, String displayName, String photoURL, Boolean emailVerified, String first_name, String last_name, String user_name, String address, String mob_no, String car_reg_no, String userType, double wallet) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.photoURL = photoURL;
        this.emailVerified = emailVerified;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.address = address;
        this.mob_no = mob_no;
        this.car_reg_no = car_reg_no;
        this.userType = userType;
        this.wallet = wallet;
    }





    public User() {}


}

