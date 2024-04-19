package com.example5.cemakkaya.controller;

import com.example5.cemakkaya.database.History;
import com.example5.cemakkaya.database.repository.HistoryRepository;
import com.example5.cemakkaya.database.service.HistoryService;
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
public class HistoryController {

    private final HistoryService historyService;

    private final HistoryRepository historyRepository;

    public HistoryController(HistoryService historyService, HistoryRepository historyRepository) {
        this.historyService = historyService;
        this.historyRepository = historyRepository;
    }


    @GetMapping("/history")
    @RolesAllowed({Roles.admin})
    public List<History> getAllHistory () {
        return historyService.findAll();
    }

    @GetMapping("/history/{id}")
    @RolesAllowed({Roles.admin, Roles.worker})
    public ResponseEntity<History> getHistoryWithId(@PathVariable Long id) {
        try {
            History history = historyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found"));
            return new ResponseEntity<>(history, HttpStatus.FOUND);
        } catch (ResponseStatusException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/history/update/{id}")
    @RolesAllowed({Roles.admin})
    public History updateHistory(@PathVariable Long id, @RequestBody History updatedHistory) {
        return historyService.editHistory(updatedHistory, id);
    }

    @DeleteMapping("/history/delete/{id}")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<String> deleteHistory(@PathVariable Long id) {
        try {
            historyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found"));
            historyService.deleteHistory(id);
            return new ResponseEntity<>("User " + id + " deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
