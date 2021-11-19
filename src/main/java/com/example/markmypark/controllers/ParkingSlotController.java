package com.example.markmypark.controllers;

import com.example.markmypark.entity.ParkingSlot;
import com.example.markmypark.entity.User;
import com.example.markmypark.repositories.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkingslots")
public class ParkingSlotController {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @RequestMapping("/getall")
    public List<ParkingSlot> getAllParkingSlots(){
        return parkingSlotRepository.findAll();
    }
}
