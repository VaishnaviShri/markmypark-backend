package com.example.markmypark.repositories;

import com.example.markmypark.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkerRepository extends MongoRepository<Worker, String> {
}
