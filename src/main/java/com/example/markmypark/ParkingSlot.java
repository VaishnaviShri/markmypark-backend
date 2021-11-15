package com.example.markmypark;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class ParkingSlot {

    @Id
    public String id;
    public String location;
    public double rate;
    public Date date;
    public int wlistno;
    public int workers_employed;
}
