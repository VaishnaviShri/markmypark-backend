package com.example.markmypark.controllers;

import com.example.markmypark.entity.User;
import com.example.markmypark.entity.Worker;
import com.example.markmypark.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @RequestMapping("/getall")
    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }
}
