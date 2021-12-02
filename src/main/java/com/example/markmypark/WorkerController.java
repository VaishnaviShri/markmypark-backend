package com.example.markmypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @PostMapping("/add")
    Worker addWorker(@RequestBody Worker newWorker) {
        return workerRepository.save(newWorker).block();
    }

    @RequestMapping("/getall")
    public List<Worker> getAllWorkers(){
        return (List<Worker>) workerRepository.findAll();
    }
}
