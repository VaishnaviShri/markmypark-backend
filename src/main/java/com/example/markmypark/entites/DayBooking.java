package com.example.markmypark.entites;

import java.util.*;

public class DayBooking {
    public Date date;
    public Map<Integer, String> usersList;//map with key being hour and value being user id of the user booked for that hour or null if the hour is free
}
