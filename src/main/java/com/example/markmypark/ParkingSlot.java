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
    public String workerID ="worker1";
    public Worker worker = null;
    public double parkingRatePerHour;
    public double totalPrice;
    public ArrayList<DayBooking> allBookings = new ArrayList<>();

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getParkingRatePerHour() {
        return parkingRatePerHour;
    }

    WorkerService workerService = new WorkerService();
    public void setTotalPrice() {
        this.totalPrice = parkingRatePerHour + Objects.requireNonNull(worker).getRatePerHour();
    }

    public int book(String userID, String dt, int checkin, int checkout) {
        if(this.allBookings.size() == 0) {
            DayBooking dbook = new DayBooking(dt);

            for(int i = checkin; i < checkout; i++)
                dbook.setHour(i, userID);

            this.allBookings.add(dbook);
        }
        else {
            int found = 0;

            for(DayBooking dbook : this.allBookings) {
                if(dbook.date.equals(dt)) {                     //object for that date exists in the list
                    found = 1;
                    for(int i = checkin; i < checkout; i++)
                        if(dbook.getUserIDatHour(i) == null)
                            dbook.setHour(i, userID);
                        else
                            return 0;                           //booking unsuccessful as there was already an existing booking b/w checkin and checkout time

                    break;
                }
            }

            if(found == 0) {                                    //object for that date doesn't exist in list, need to create new
                DayBooking dbook = new DayBooking(dt);

                for(int i = checkin; i < checkout; i++)
                    dbook.setHour(i, userID);

                this.allBookings.add(dbook);
            }
        }

        return 1;       //booking successful
    }

    public void pSlotRelease(String userID, String dt, int checkin, int checkout) {
        for(DayBooking dbook : this.allBookings) {
            if(dbook.date.equals(dt)) {
                for(int i = checkin; i< checkout; i++) {
                    if(dbook.getUserIDatHour(i).equals(userID))
                        dbook.setHour(i, null);
                }
            }
        }
    }

    public void setWorker() throws ExecutionException, InterruptedException {
        this.worker = workerService.getWorker(workerID);
    }

    public ParkingSlot() throws ExecutionException, InterruptedException {
        setWorker();
    }

    public ParkingSlot(String location, String workerID, double parkingRatePerHour) throws ExecutionException, InterruptedException {
        this.location = location;
        this.worker = workerService.getWorker(workerID);
        this.parkingRatePerHour = parkingRatePerHour;
    }

    //if no match found display based on location

}
