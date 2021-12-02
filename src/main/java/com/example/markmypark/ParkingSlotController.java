package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parkingslots")
public class ParkingSlotController {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @PostMapping("/add")
    ParkingSlot addParkingSlot(@RequestBody ParkingSlot newParkingSlot) {
        return parkingSlotRepository.save(newParkingSlot).block();
    }

    @RequestMapping("/getall")
    public List<ParkingSlot> getAllParkingSlots(){
        return (List<ParkingSlot>) parkingSlotRepository.findAll();
    }

    @RequestMapping("/filter")
    public List<ParkingSlot> filterParkingSlots(
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("check_in") int checkIn,
            @RequestParam("check_out") int checkOut){

        List<ParkingSlot> allSlots = (List<ParkingSlot>) parkingSlotRepository.findAll();
        List<ParkingSlot> match = new ArrayList<>();
        for(ParkingSlot obj : allSlots){
            if(obj.location != null && obj.location.equals(location)){
                for(DayBooking db : obj.allBookings){
                    if(db.date.equals(date)){
                        int flag = 0;
                        for(int i = checkIn; i <= checkOut; i++){
                            if(db.usersList.get(i) != null){
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0)
                            match.add(obj);
                    }
                }
            }
        }
            return match;
    }
}
