package com.example.markmypark.entity;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Worker {
    @Id
    public String workerID;
    public int rating;
    public Map<String, Double> amenities = Map.of("car cleaning", 560.0, "air pressure check", 69.0);
    public double ratePerHour;

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour() {
        int ratePerHour =0;
        for(double price : amenities.values()){
            ratePerHour +=price;
        }
        this.ratePerHour = ratePerHour;
    }



    public Worker(String workerID, int rating, Map<String, Double> amenities) {
        this.workerID = workerID;
        this.rating = rating;
        this.amenities = amenities;
        setRatePerHour();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Map<String, Double> getAmenities() {
        return amenities;
    }

    public void setAmenities(Map<String, Double> amenities) {
        this.amenities = amenities;
    }
}