package com.example.markmypark;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class Booking {

    @Id
    public int refno;
    public int slot_id;
    public Date checkin;
    public Date checkout;
    public int billamt;

}
