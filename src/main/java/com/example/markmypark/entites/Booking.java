package com.example.markmypark.entites;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class Booking {

    @Id
    public int bookingID;
    public String date;
    public int refNo;
    public String pSlotID;
    public int checkin;
    public int checkout;
    public double billAmount;
    public boolean checkedout = false;

    public Booking(int refNo, String pSlotID, String date, int checkin, int checkout) {
        this.refNo = refNo;
        this.pSlotID = pSlotID;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Booking() {}

    public int getRefNo() {
        return refNo;
    }

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }

    public String getpSlotID() {
        return pSlotID;
    }

    public void setSlotID(String slotID) {
        this.pSlotID = slotID;
    }

    public int getCheckin() {
        return checkin;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public int getCheckout() {
        return checkout;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }
}
