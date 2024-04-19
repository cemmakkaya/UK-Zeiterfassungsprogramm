package com.example5.cemakkaya;

import com.example5.cemakkaya.controller.AdministerWorkerController;
import com.example5.cemakkaya.database.Worker;
import com.example5.cemakkaya.database.repository.WorkerRepository;
import com.example5.cemakkaya.database.service.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestControllerTests {

    @Mock
    private WorkerService workerService;

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private AdministerWorkerController workerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Long id = 1L;
    private Worker worker1 = new Worker(1L, "Test1", "Test2", "test1.test2");
    private Worker worker2 = new Worker(2L, "Test2", "Test3", "test2.test3");

    @Test
    void testGetWorkerById() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker1));
        ResponseEntity<Worker> response = workerController.getWorkerWithId(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(worker1, response.getBody());
    }

    @Test
    void testCreateWorker() {
        when(workerRepository.save(any(Worker.class))).thenReturn(worker1);
        ResponseEntity<Worker> response = workerController.createWorker(worker1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(worker1, response.getBody());
    }

    @Test
    void testUpdateWorker() {
        when(workerService.editWorker(worker1, id)).thenReturn(worker2);

        Worker actualWorker = workerController.updateWorker(id, worker1);

        verify(workerService, times(1)).editWorker(worker1, id);
        assertEquals(worker2, actualWorker);
    }

    @Test
    void testDeleteWorker() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker1));
        ResponseEntity<String> response = workerController.deleteWorker(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Worker 1 deleted.", response.getBody());
    }
}
