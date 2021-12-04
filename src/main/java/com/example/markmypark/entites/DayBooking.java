package com.example.markmypark.entites;

import java.util.*;

public class DayBooking {
    public String date;
    public Map<Integer, String> usersList = new TreeMap<Integer, String>();//map with key being hour and value being user id of the user booked for that hour or null if the hour is free

    public void setHour(int h, String userID) {
        this.usersList.put(h, userID);
    }
}
