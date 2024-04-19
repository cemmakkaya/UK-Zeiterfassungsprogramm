package com.example5.cemakkaya.controller;

import com.example5.cemakkaya.database.Activity;
import com.example5.cemakkaya.database.repository.ActivityRepository;
import com.example5.cemakkaya.database.service.ActivityService;
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
public class AdministerActivityController {

    private final ActivityService activityService;
    private final ActivityRepository activityRepository;

    public AdministerActivityController(ActivityService activityService, ActivityRepository activityRepository) {
        this.activityService = activityService;
        this.activityRepository = activityRepository;
    }

    @GetMapping("/activity")
    @RolesAllowed({Roles.admin})
    public List<Activity> getAllActivities () {
        return activityService.findAll();
    }

    @GetMapping("/activity/{id}")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<Activity> getActivityId(@PathVariable Long id) {
        try {
            Activity activity = activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found"));
            return new ResponseEntity<>(activity, HttpStatus.FOUND);
        } catch (ResponseStatusException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/activity/add")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        try {
            activity = activityRepository.save(activity);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activity/update/{id}")
    @RolesAllowed({Roles.admin})
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return activityService.editActivity(activity, id);
    }

    @DeleteMapping("/activity/delete/{id}")
    @RolesAllowed({Roles.admin})
    public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
        try {
            activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found"));
            activityService.deleteActivity(id);
            return new ResponseEntity<>("Activity " + id + " deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

}
