package com.example.markmypark;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;


import java.util.List;

public interface  UserRepository extends FirestoreReactiveRepository<User> {

    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);


}