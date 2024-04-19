package com.example5.cemakkaya.database.service;

import com.example5.cemakkaya.database.Worker;
import com.example5.cemakkaya.database.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository userRepository;

    public WorkerService(WorkerRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Worker> findAll() {
        return userRepository.findAll();
    }

    public Optional<Worker> findById(Long id) {
        return userRepository.findById(id);
    }

    public Worker editWorker(Worker worker, Long id) {
        if (userRepository.existsById(id)) {
            Worker workerToEdit = new Worker(id, worker.getName(), worker.getPrename(), worker.getUsername(), worker.getTimeBalance());
            return userRepository.save(workerToEdit);
        } else {
            throw new NoSuchElementException("Das Objekt mit der ID: " + id + " wurde nicht gefunden.");
        }
    }

    public void deleteWorker(Long id) {
        userRepository.deleteById(id);
    }
}
