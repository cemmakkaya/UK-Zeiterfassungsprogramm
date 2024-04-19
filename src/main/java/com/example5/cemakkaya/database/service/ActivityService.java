package com.example5.cemakkaya.database.service;

import com.example5.cemakkaya.database.Activity;
import com.example5.cemakkaya.database.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ActivityService {

    public final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity editActivity(Activity activity, Long id) {
        if (activityRepository.existsById(id)) {
            Activity activityToEdit = new Activity(id, activity.getActivity());
            return activityRepository.save(activityToEdit);
        } else {
            throw new NoSuchElementException("Das Objekt mit der ID: " + id + " wurde nicht gefunden.");
        }
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
