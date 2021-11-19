package com.example.markmypark.repositories;

import com.example.markmypark.entity.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String >{
    public ParkingSlot findByLocation(String location);
}
