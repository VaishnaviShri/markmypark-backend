package com.example.markmypark;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initalize(){
        try {
            //String path ="C:\\sem 3-1\\OOP\\project\\markmypark\\markmypark\\serviceAccountKey.json";

            FileInputStream serviceAccount =
                    new FileInputStream("path/to/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://markmypark-4ee85-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
