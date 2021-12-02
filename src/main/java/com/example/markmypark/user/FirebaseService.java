package com.example.markmypark.user;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.id).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {

        List<User> usersList = List.of(new User[]{new User()});
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
           usersList.add(document.toObject(User.class));
        }
        return usersList;

    }
    public User getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection("users").document(id);
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

}
