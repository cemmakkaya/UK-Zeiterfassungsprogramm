package com.example5.cemakkaya.controller;


import com.example5.cemakkaya.database.service.HistoryService;
import com.example5.cemakkaya.database.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class StampController {

    private final WorkerService userService;
    private final HistoryService historyService;

    public StampController(WorkerService userService, HistoryService historyService) {
        this.userService = userService;
        this.historyService = historyService;
    }

    
}
