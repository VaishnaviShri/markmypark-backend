package com.example.markmypark;

import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collectionName = "workers")
public class Worker {
    @DocumentId
    public String workerID;
    public String workerName;
    public double rating;
    public int noOfReviews =0;
    public List<String> amenities = new ArrayList<>();
    public double ratePerHour;

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour() {
        int ratePerHour=0;
        if(amenities!=null)
            ratePerHour = amenities.size() * 50; // each service costs Rs. 50
        this.ratePerHour = ratePerHour;
    }
    public Worker(){

    }



    public Worker(String workerID, double rating, int noOfReviews, List<String> amenities) {
        this.workerID = workerID;
        this.rating = rating;
        this.noOfReviews =noOfReviews;
        this.amenities = amenities;
        setRatePerHour();
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
