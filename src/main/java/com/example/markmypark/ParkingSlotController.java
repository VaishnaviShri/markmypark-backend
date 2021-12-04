package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/parkingslots")
public class ParkingSlotController {


    private ParkingSlotService parkingSlotService = new ParkingSlotService();


    @PostMapping("/add")
    String addParkingSlot(@RequestBody ParkingSlot newParkingSlot) throws ExecutionException, InterruptedException {
        return parkingSlotService.saveParkingSlot(newParkingSlot);
    }

    @RequestMapping("/getall")
    public List<ParkingSlot> getAllParkingSlots() throws ExecutionException, InterruptedException {
        return (List<ParkingSlot>) parkingSlotService.getAllSlots();
    }

    @RequestMapping("/filter")
    public List<ParkingSlot> filterParkingSlots(
            @RequestParam("location") String location,
            @RequestParam("date") String date,
            @RequestParam("check_in") int checkIn,
            @RequestParam("check_out") int checkOut) throws ExecutionException, InterruptedException {

     return parkingSlotService.getFilteredSlots(location, date, checkIn, checkOut);
    }

    @RequestMapping("/getbyid")
    public ParkingSlot getSlotById(
            @RequestParam("id") String id
    ) throws ExecutionException, InterruptedException {
        return parkingSlotService.getSlotById(id);
    }

    //when no matching slots found display all the slots for the searched location
    @RequestMapping("/getbylocation")
    public List<ParkingSlot> getSlotsByLocation(
            @RequestParam("location") String location
    ) throws ExecutionException, InterruptedException {
        return parkingSlotService.getSLotsByLocation(location);
    }
    @RequestMapping("getalllocations")
    public List<String> getAllLocations() throws ExecutionException, InterruptedException {
        return parkingSlotService.getlocationList();
    }

}
