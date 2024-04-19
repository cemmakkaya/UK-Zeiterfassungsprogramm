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
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository userRepository, WorkerRepository workerRepository) {
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll() {
        return userRepository.findAll();
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker editWorker(Worker worker, Long id) {
        if (userRepository.existsById(id)) {
            Worker workerToEdit = new Worker(id, worker.getName(), worker.getPrename(), worker.getUsername());
            return userRepository.save(workerToEdit);
        } else {
            throw new NoSuchElementException("Das Objekt mit der ID: " + id + " wurde nicht gefunden.");
        }
    }

    public void deleteWorker(Long id) {
        userRepository.deleteById(id);
    }
}
