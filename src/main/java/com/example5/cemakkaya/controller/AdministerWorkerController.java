package com.example5.cemakkaya.controller;

import com.example5.cemakkaya.database.Worker;
import com.example5.cemakkaya.database.repository.WorkerRepository;
import com.example5.cemakkaya.database.service.WorkerService;
import com.example5.cemakkaya.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class AdministerWorkerController {

    private final WorkerRepository workerRepository;

    private WorkerService workerService;

    public AdministerWorkerController(WorkerService workerService, WorkerRepository workerRepository) {
        this.workerService = workerService;
        this.workerRepository = workerRepository;}

    @GetMapping("/worker")
    @RolesAllowed({Roles.admin})
    public List<Worker> getAllWorker () {
        return workerService.findAll();
    }

    @GetMapping("/worker/{id}")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<Worker> getWorkerWithId(@PathVariable Long id) {
        try {
            Worker worker = workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker not found"));
            return new ResponseEntity<>(worker, HttpStatus.FOUND);
        } catch (ResponseStatusException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/worker/add")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        try {
            worker = workerRepository.save(worker);
            return new ResponseEntity<>(worker, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/worker/update/{id}")
    @RolesAllowed({Roles.admin})
    public Worker updateWorker(@PathVariable Long id, @RequestBody Worker updatedWorker) {
        return workerService.editWorker(updatedWorker, id);
    }

    @DeleteMapping("/worker/delete/{id}")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<String> deleteWorker(@PathVariable Long id) {
        try {
            workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker not found"));
            workerService.deleteWorker(id);
            return new ResponseEntity<>("Worker " + id + " deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

}
