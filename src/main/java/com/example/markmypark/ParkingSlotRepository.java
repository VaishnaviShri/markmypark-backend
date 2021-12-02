package com.example.markmypark;

import com.example.markmypark.ParkingSlot;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;


public interface ParkingSlotRepository extends FirestoreReactiveRepository<ParkingSlot> {
    //public ParkingSlot findByLocation(String location);
}
