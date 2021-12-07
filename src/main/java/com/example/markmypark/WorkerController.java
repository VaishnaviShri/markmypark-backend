package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private WorkerService workerService = new WorkerService();

    @PostMapping("/add")
    String addWorker(@RequestBody Worker newWorker) throws ExecutionException, InterruptedException {
        return workerService.saveWorker(newWorker);
    }

    @DeleteMapping("/delete")
    String deleteWorker(
            @RequestParam String workerID) throws ExecutionException, InterruptedException {
        return workerService.deleteWorker(workerID).toString();
    }

    @GetMapping("/getall")
    public List<Worker> getAllWorkers() throws ExecutionException, InterruptedException {
        return  workerService.getAllWorkers();
    }

    @GetMapping("/getbyid")
    public Worker getWorkerById(
            @RequestParam String id
    ) throws ExecutionException, InterruptedException {
        return workerService.getWorker(id);
    }

    @GetMapping("/getServicesTotal")
    public double getServicesTotal(
            @RequestParam String id
    ) throws ExecutionException, InterruptedException {
        return workerService.getWorker(id).getRatePerHour();
    }

    //TODO: function to update worker rating with new average


}
