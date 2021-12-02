package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private WorkerService workerService;

    @PostMapping("/add")
    String addWorker(@RequestBody Worker newWorker) throws ExecutionException, InterruptedException {
        return workerService.saveWorker(newWorker);
    }

    @RequestMapping("/getall")
    public List<Worker> getAllWorkers() throws ExecutionException, InterruptedException {
        return  workerService.getAllWorkers();
    }

    @RequestMapping("/getbyid")
    public Worker getWorkerById(
            @RequestParam String id
    ) throws ExecutionException, InterruptedException {
        return workerService.getWorker(id);
    }

    //TODO: function to update worker rating with new average


}
