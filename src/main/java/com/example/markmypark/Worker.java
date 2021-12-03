package com.example.markmypark;

import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.cloud.gcp.data.firestore.Document;



import java.util.Map;

@Document(collectionName = "workers")
public class Worker {
    @DocumentId
    public String workerID;
    public double rating;
    public int noOfReviews =0;
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



    public Worker(String workerID, double rating, int noOfReviews, Map<String, Double> amenities) {
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

    public Map<String, Double> getAmenities() {
        return amenities;
    }

    public void setAmenities(Map<String, Double> amenities) {
        this.amenities = amenities;
    }
}
