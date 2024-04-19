package com.example5.cemakkaya.controller;


import com.example5.cemakkaya.database.History;
import com.example5.cemakkaya.database.Worker;
import com.example5.cemakkaya.database.repository.HistoryRepository;
import com.example5.cemakkaya.database.service.HistoryService;
import com.example5.cemakkaya.database.service.HistoryService;
import com.example5.cemakkaya.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class StampController {

    private final HistoryService userService;
    private final HistoryService historyService;
    private final HistoryRepository historyRepository;

    public StampController(HistoryService userService, HistoryService historyService, HistoryRepository historyRepository) {
        this.userService = userService;
        this.historyService = historyService;
        this.historyRepository = historyRepository;
    }

    @PostMapping("/stamp/add")
    @RolesAllowed({Roles.admin, Roles.worker})
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        try {
            history.setDate(LocalDate.now());
            history.setMorning_in(LocalDateTime.now());

            history = historyRepository.save(history);
            return new ResponseEntity<>(history, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/stamp/update/{id}")
    @RolesAllowed({Roles.admin, Roles.worker})
    public History updateWorker(@PathVariable Long id, @RequestBody History history) {
        return historyService.editHistory(history, id);
    }

}
