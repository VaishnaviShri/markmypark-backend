package com.example.markmypark.entites;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class Booking {

    @Id
    public int bookingID;
    public int refNo;
    public String pSlotID;
    public int checkin;
    public int checkout;
    public double billAmount;

    public Booking(int refNo, String pSlotID, int checkin, int checkout) {
        this.refNo = refNo;
        this.pSlotID = pSlotID;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Booking() {}

    public void setBillAmt(double b_amt) { this.billAmount = b_amt; }

    public int getRefNo() {
        return refNo;
    }

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }

    public String getSlotID() {
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
