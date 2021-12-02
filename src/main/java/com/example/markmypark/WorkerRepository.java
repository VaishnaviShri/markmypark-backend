package com.example.markmypark;

import com.example.markmypark.Worker;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;


public interface WorkerRepository extends FirestoreReactiveRepository<Worker> {
}
