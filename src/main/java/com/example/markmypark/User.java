package com.example.markmypark;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "new_users")
public class User {

    @Id
    public String id;
    public String firstName;
    public String lastName;

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

    public String getName() {
        return firstName.concat(lastName);
    }
}

