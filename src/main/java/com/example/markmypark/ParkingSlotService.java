package com.example.markmypark;

import com.example.markmypark.entites.DayBooking;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParkingSlotService {
    ParkingSlotService(){

    }
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveParkingSlot(ParkingSlot parkingSlot) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("parking_slots").document(parkingSlot.id).set(parkingSlot);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<ParkingSlot> getAllSlots() throws ExecutionException, InterruptedException {

        List<ParkingSlot> parkingSlots = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("parking_slots").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            parkingSlots.add(document.toObject(ParkingSlot.class));
        }
        return parkingSlots;

    }
    public List<ParkingSlot> getFilteredSlots(String location, String date, int check_in, int check_out) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("parking_slots")
                .whereEqualTo("location", location)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ParkingSlot> allParkingSlots = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            allParkingSlots.add(document.toObject(ParkingSlot.class));
        }
        return filterListAtLocation(allParkingSlots, date, check_in, check_out);
    }

    public List<ParkingSlot> filterListAtLocation (List<ParkingSlot> allSlots, String date, int checkIn, int checkOut){
        List<ParkingSlot> match = new ArrayList<>();
        for(ParkingSlot obj : allSlots){

                for(DayBooking db : obj.allBookings){
                    if(db.date.equals(date)){
                        int flag = 0;
                        for(int i = checkIn; i <= checkOut; i++){
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
        return match;
    }
    public List<ParkingSlot> getSLotsByLocation(String location) throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("parking_slots")
                .whereEqualTo("location", location)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ParkingSlot> allParkingSlots = List.of(new ParkingSlot[]{new ParkingSlot("","",0)});
        for (QueryDocumentSnapshot document : documents) {
            allParkingSlots.add(document.toObject(ParkingSlot.class));
        }
        return allParkingSlots;
    }

    public ParkingSlot getSlotById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection("parking_slots").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(ParkingSlot.class);
    }
}
