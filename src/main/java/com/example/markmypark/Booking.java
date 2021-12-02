package com.example.markmypark;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class Booking {

    @Id
    public int bookingID;
    public int refNo;
    public int slotID;
    public Date checkin;
    public Date checkout;
    public int billAmount;

    public Booking(int refNo, int slotId, Date checkin, Date checkout, int billAmount) {
        this.refNo = refNo;
        this.slotID = slotId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.billAmount = billAmount;
    }

    public int getRefNo() {
        return refNo;
    }

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }
}
