package com.example.markmypark;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String >{
    public ParkingSlot findByLocation(String location);
}
