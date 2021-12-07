package com.example.markmypark;


import com.example.markmypark.config.EmailConfig;
import com.example.markmypark.entites.Booking;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("new_users").document(user.uid).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {

        List<User> usersList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("new_users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            usersList.add(document.toObject(User.class));
        }
        return usersList;

    }
    public User getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = dbFirestore.collection("new_users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        User user = new User();
        if (document.exists()) {
            user=document.toObject(User.class);
        } else {
            System.out.println("No such document!");
        }
        return user;
    }
    // first name, last name, mo no and address

    public String updateUser(String uid, String firstName, String lastName, String mobNo, String address) throws ExecutionException, InterruptedException {
        User user = getUser(uid);
        if(!Objects.equals(firstName, ""))
            user.first_name = firstName;
        if(!Objects.equals(lastName, ""))
            user.last_name = lastName;
        if(!Objects.equals(mobNo, ""))
            user.mob_no = mobNo;
        if(!Objects.equals(address, ""))
            user.address = address;

        return saveUserDetails(user);
    }

    public User addUserBooking(String userID, String parkingSlotID, String date, int checkin, int checkout, int refno) throws ExecutionException, InterruptedException {
        User u_obj = getUser(userID);
        Booking book_obj = new Booking(refno, parkingSlotID, date, checkin, checkout);
        ParkingSlot pslot_obj = new ParkingSlotService().getSlotById(parkingSlotID);
        double b_amt = (checkout - checkin) * pslot_obj.getParkingRatePerHour();
        book_obj.setBillAmount(b_amt);
        u_obj.book(book_obj);
        u_obj.minAmtDeduction();

        saveUserDetails(u_obj);

        String txt = "Dear User,\nThanks for booking with MMP.\n Your booking details are:-\n"
                     .concat("\nBooking Reference Number: ").concat(Integer.toString(refno))
                     .concat("\nLocation: ").concat(pslot_obj.location)
                     .concat("\nDate: ").concat(date)
                     .concat("\nCheck In: ").concat(Integer.toString(checkin)).concat(":00")
                     .concat("\nCheck Out: ").concat(Integer.toString(checkout)).concat(":00")
                     .concat("\nRate Per Hour: ").concat(Double.toString(pslot_obj.getParkingRatePerHour()))
                     .concat("\nBill Amount: ").concat(Double.toString(b_amt));

        String subj = "MMP Booking ".concat(Integer.toString(refno));

        new EmailConfig().defMailSender(u_obj.email, subj, txt);

        return u_obj;
    }

    //test
    public String getCurrentHour() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        String dthour = dateTime.format(formatter);

        return dthour;
    }

    //test
/*    public void checkout() throws ExecutionException, InterruptedException {
        //User u_obj;
        //ParkingSlot p_obj;
        //Booking b_obj;

        int u = 0;

        for(User u_obj : getAllUsers()) {
            int b = 0;
            for(Booking b_obj : u_obj.bookingList) {
                if(!b_obj.checkedout && getCurrentHour().substring(0, 10).equals(b_obj.date) && getCurrentHour().substring(11, 13).equals(Integer.toString(b_obj.getCheckout()))) {
                    ParkingSlot p_obj = new ParkingSlotService().getSlotById(b_obj.getpSlotID());
                    p_obj.pSlotRelease(u_obj.uid, b_obj.date, b_obj.getCheckin(), b_obj.getCheckout());
                    u_obj.updateWallet(-(b_obj.getBillAmount() - 100));
                    b_obj.checkedout = true;
                    new ParkingSlotService().saveParkingSlot(parkingSlot);

                }
                b++;
            }
            u++;
        }
    }*/

    //original
    public void checkout(String userID, String parkingSlotID, int refno) throws ExecutionException, InterruptedException {
        User u_obj = getUser(userID);
        ParkingSlot parkingSlot = new ParkingSlotService().getSlotById(parkingSlotID);
        for(Booking b : u_obj.bookingList) {
            if(!b.checkedout && b.getRefNo() == refno) {
                parkingSlot.pSlotRelease(userID, b.date, b.getCheckin(), b.getCheckout());
                u_obj.updateWallet(-(b.getBillAmount() - 100));
                b.checkedout = true;
                saveUserDetails(u_obj);

                new ParkingSlotService().saveParkingSlot(parkingSlot);

                String txt = "Dear User,\nYou have checked out of MMP Parking Slot ".concat(parkingSlotID)
                        .concat(".\nYour Bill Details are:-\nWallet Balance: ").concat(Double.toString(u_obj.wallet))
                        .concat("\nBooking Reference Number: ").concat(Integer.toString(b.getRefNo()))
                        .concat("\nDate: ").concat(b.date)
                        .concat("\nCheck In: ").concat(Integer.toString(b.checkin)).concat(":00")
                        .concat("\nCheck Out: ").concat(Integer.toString(b.checkout)).concat(":00")
                        .concat("\nRate Per Hour: ").concat(Double.toString(parkingSlot.parkingRatePerHour))
                        .concat("\nBill Amount: ").concat(Double.toString(b.billAmount));

                String subj = "MMP Checkout ".concat(Integer.toString(b.getRefNo()));

                new EmailConfig().defMailSender(u_obj.email, subj, txt);
            }
        }

        //saveUserDetails(u_obj);
        //new ParkingSlotService().saveParkingSlot(parkingSlot);

        /*String txt = "Dear User,\nYou have checked out of MMP Parking Slot ".concat(parkingSlotID)
                     .concat(".\nYour Bill Details are:-\nWallet Balance: ").concat(Double.toString(u_obj.wallet))
                     .concat("\nBooking Reference Number: ").concat(Integer.toString(b_obj.getRefNo()))
                     .concat("\nDate: ").concat(b_obj.date)
                     .concat("\nCheck In: ").concat(Integer.toString(b_obj.checkin)).concat(":00")
                     .concat("\nCheck Out: ").concat(Integer.toString(b_obj.checkout)).concat(":00")
                     .concat("\nRate Per Hour: ").concat(Double.toString(parkingSlot.parkingRatePerHour))
                     .concat("\nBill Amount: ").concat(Double.toString(b_obj.billAmount));

        String subj = "MMP Checkout ".concat(Integer.toString(b_obj.getRefNo()));

        new EmailConfig().defMailSender(u_obj.email, subj, txt);*/
    }

    public void promocode_check(String userID) throws ExecutionException, InterruptedException {
        User u_obj = getUser(userID);
        if (u_obj.bookingList.size() >= 5) {
            String subj = "You have got a promocode from MMP";
            String promo = "get10off";
            String txt = "Thanks for booking with us frequently.\nHere is your promocode for future bookings: ".concat(promo);
            new EmailConfig().defMailSender(u_obj.email, subj, txt);
        }
    }

}
