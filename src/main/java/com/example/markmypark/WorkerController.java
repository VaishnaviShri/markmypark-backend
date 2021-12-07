package com.example.markmypark;

import com.google.cloud.firestore.DocumentReference;
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

    @GetMapping("/getservicestotal")
    public double getServicesTotal(
            @RequestParam String id
    ) throws ExecutionException, InterruptedException {
        return workerService.getWorker(id).getRatePerHour();
    }

    @PutMapping("/updaterating")
    public void updateRating(
            @RequestParam String id,
            @RequestParam double rating
    ) throws ExecutionException, InterruptedException {
        Worker worker = workerService.getWorker(id);
        double currentRating = worker.rating;
        double numberOfReviews = worker.noOfReviews;
        worker.rating = (currentRating * numberOfReviews + rating)/ (numberOfReviews +1);
        worker.noOfReviews++;
        workerService.saveWorker(worker);
    }
}
