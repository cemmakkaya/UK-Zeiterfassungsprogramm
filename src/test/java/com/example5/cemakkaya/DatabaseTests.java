package com.example5.cemakkaya;

import com.example5.cemakkaya.database.Worker;
import com.example5.cemakkaya.database.repository.WorkerRepository;
import com.example5.cemakkaya.database.service.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DatabaseTests {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Long id = 1L;

    private Worker worker1 = new Worker(id, "Test1", "Test2", "test1.test2");
    private Worker worker2 = new Worker(2L, "Test2", "Test3", "test2.test3");


    @Test
    void testCreateWorker() {
        when(workerRepository.save(worker1)).thenReturn(worker2);
        Worker result = workerService.saveWorker(worker1);

        assertEquals(worker2, result);
        verify(workerRepository).save(worker1);
    }

    @Test
    void testReadAllWorkers() {
        List<Worker> expectedWorkers = new ArrayList<>();
        expectedWorkers.add(worker1);
        expectedWorkers.add(worker2);
        when(workerRepository.findAll()).thenReturn(expectedWorkers);
        List<Worker> actualWorkers = workerService.findAll();

        assertEquals(expectedWorkers.size(), actualWorkers.size());
        assertEquals(expectedWorkers, actualWorkers);
    }

    @Test
    public void testEditWorker_Exists() {
        when(workerRepository.existsById(id)).thenReturn(true);
        when(workerRepository.save(any(Worker.class))).thenReturn(worker1);
        Worker editedWorker = workerService.editWorker(worker1, id);

        verify(workerRepository, times(1)).existsById(id);
        verify(workerRepository, times(1)).save(any(Worker.class));

        assertEquals(worker1.getName(), editedWorker.getName());
        assertEquals(worker1.getPrename(), editedWorker.getPrename());
        assertEquals(worker1.getUsername(), editedWorker.getUsername());
    }

    @Test
    void testDeleteWorker() {
        workerService.deleteWorker(id);
        verify(workerRepository, times(1)).deleteById(id);
    }
}
