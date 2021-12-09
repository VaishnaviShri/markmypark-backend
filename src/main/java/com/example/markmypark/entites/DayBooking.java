package com.example.markmypark.entites;

import java.util.*;

public class DayBooking {
    public String date;
    //public Map<Integer, String> usersList = new TreeMap<Integer, String>();//map with key being hour and value being user id of the user booked for that hour or null if the hour is free
    public List <String> usersList = new ArrayList<>();

    public String getUserIDatHour(int h) { return usersList.get(h - 10); }

    public void setHour(int h, String userID) {
        this.usersList.set(h - 10, userID);
    }

    public DayBooking() {
        for(int i = 10; i <= 17; i++)
            this.usersList.add(i - 10, null);
    }

    public DayBooking(String dt) {
        this();
        this.date = dt;
    }
}
