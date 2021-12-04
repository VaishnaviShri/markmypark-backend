package com.example.markmypark;


import com.example.markmypark.entites.Booking;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("new_users").document(user.uid).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {

        List<User> usersList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("new_users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
           usersList.add(document.toObject(User.class));
        }
        return usersList;

    }
    public User getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection("new_users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        User user = new User();
        if (document.exists()) {
            user=document.toObject(User.class);
        } else {
            System.out.println("No such document!");
        }
        return user;
    }

    //TODO: function to update wallet
    //TODO: function to add booking object to a user with a particular id
    public User addUserBooking(int h, String userID, String parkingSlotID, int checkin, int checkout, int refno) throws ExecutionException, InterruptedException {
        User u_obj = getUser(userID);
        Booking book_obj = new Booking(refno, parkingSlotID, checkin, checkout);
        u_obj.book(book_obj);

        saveUserDetails(u_obj);

        return u_obj;
    }

}
