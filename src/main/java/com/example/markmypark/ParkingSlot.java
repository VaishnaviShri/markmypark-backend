package com.example.markmypark;
import com.example.markmypark.entites.DayBooking;
import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;

import reactor.core.publisher.Mono;

import java.util.*;

@Document(collectionName = "parkingslots")
public class ParkingSlot {

    @DocumentId
    public String id;
    public String location;
    public Mono<Worker> worker;
    public double parkingRatePerHour;
    public double totalPrice;
    public ArrayList<DayBooking> allBookings;

    public double getTotalPrice() {
        return totalPrice;
    }

   // WorkerRepository workerRepository;
    public void setTotalPrice() {
        this.totalPrice = parkingRatePerHour + Objects.requireNonNull(worker.block()).getRatePerHour();
    }

    public void book(int h, String userID, Date dt) {
        for(DayBooking dbook : this.allBookings) {
            if(dbook.date.equals(dt))
                dbook.setHour(h, userID);
        }
    }

    public ParkingSlot() {

    }

    public ParkingSlot(String location, String workerID, double parkingRatePerHour) {
        this.location = location;
      //  this.worker = workerRepository.findById(workerID);
        this.parkingRatePerHour = parkingRatePerHour;
    }

    //if no match found display based on location
/*

    public ArrayList<ParkingSlot> findbyloc(String loc, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<>();
        for(ParkingSlot obj : arr){
            if(obj.location != null && obj.location.equals(loc))
                match.add(obj);
        }

        return match;
    }

    public ArrayList<ParkingSlot> find(String loc, Date dt, int ch_in, int ch_out, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<>();
        for(ParkingSlot obj : arr){
            if(obj.location != null && obj.location.equals(loc)){
                for(DayBooking db : obj.allBookings){
                    if(db.date.equals(dt)){
                        int flag = 0;
                        for(int i = ch_in; i <= ch_out; i++){
                            if(db.usersList.get(i) != null){
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0)
                            match.add(obj);
                    }
                }
            }
        }

        return match;
    }

    public ArrayList<ParkingSlot> find_by_date(Date dt, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<ParkingSlot>();
        for(ParkingSlot obj : arr){
            if(obj.date != null && obj.date.equals(dt))
                match.add(obj);
        }

        return match;
    }

    public ArrayList<ParkingSlot> find_by_checkin(Date ch_in, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<ParkingSlot>();
        for(ParkingSlot obj : arr){
            if(obj.check_in != null && obj.check_in.equals(ch_in))
                match.add(obj);
        }

        return match;
    }

    public ArrayList<ParkingSlot> find_by_checkout(Date ch_out, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<ParkingSlot>();
        for(ParkingSlot obj : arr){
            if(obj.check_out != null && obj.check_out.equals(ch_out))
                match.add(obj);
        }

        return match;
    }*/
}
