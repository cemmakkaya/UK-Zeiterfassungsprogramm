package com.example5.cemakkaya.database.service;

import com.example5.cemakkaya.database.History;
import com.example5.cemakkaya.database.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoryService {

    public final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public History editHistory(History history, Long id) {
        if (historyRepository.existsById(id)) {
            History historyToEdit = new History(id, history.getUsername(), history.getMorning_out(), history.getAfternoon_in(), history.getAfternoon_out());

            history.setDate(LocalDate.now());
            history.setMorning_in(LocalDateTime.now());

            return historyRepository.save(historyToEdit);
        } else {
            throw new NoSuchElementException("History Entry: " + id + " was not found");
        }
    }

    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
    
}
