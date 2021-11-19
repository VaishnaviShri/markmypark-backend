package com.example.markmypark;
import org.springframework.data.annotation.Id;
import java.util.*;

public class ParkingSlot {

    @Id public String id;
    public String location;
    public double rate;
    public Date date;
    public Date check_in;
    public Date check_out;
    public int wlistno;
    public int workers_employed;

    ParkingSlot(String id,
                String location,
                double rate,
                Date date,
                Date check_in,
                Date check_out) {
        this.id = id;
        this.location = location;
        this.rate = rate;
        this.date = date;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public ArrayList<ParkingSlot> find(String loc, ArrayList<ParkingSlot> arr) {
        ArrayList<ParkingSlot> match = new ArrayList<ParkingSlot>();
        for(ParkingSlot obj : arr){
            if(obj.location != null && obj.location.equals(loc))
                match.add(obj);
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
    }
}
