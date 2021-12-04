package com.example.markmypark;
import com.example.markmypark.entites.DayBooking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;

import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Document(collectionName = "parkingslots")
public class ParkingSlot {

    @DocumentId
    public String id;
    public String location;
    public Worker worker = null;
    public double parkingRatePerHour;
    public double totalPrice;
    public ArrayList<DayBooking> allBookings;

    public double getTotalPrice() {
        return totalPrice;
    }

    WorkerService workerService = new WorkerService();
    public void setTotalPrice() {
        this.totalPrice = parkingRatePerHour + Objects.requireNonNull(worker).getRatePerHour();
    }

    public void book(int h, String userID, Date dt) {
        for(DayBooking dbook : this.allBookings) {
            if(dbook.date.equals(dt))
                dbook.setHour(h, userID);
        }
    }

    public ParkingSlot() {

    }

    public ParkingSlot(String location, String workerID, double parkingRatePerHour) throws ExecutionException, InterruptedException {
        this.location = location;
       this.worker =workerService.getWorker(workerID);
        this.parkingRatePerHour = parkingRatePerHour;
    }

    //if no match found display based on location

}
