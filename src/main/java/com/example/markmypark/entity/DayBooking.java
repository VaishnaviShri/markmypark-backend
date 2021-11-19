package com.example.markmypark.entity;

import java.util.Date;
import java.util.List;

public class DayBooking {
    public Date date;
    public List<String> usersList;//map with key beng hour and value being user id of the user booked for that hour or null if the hour is free
}
