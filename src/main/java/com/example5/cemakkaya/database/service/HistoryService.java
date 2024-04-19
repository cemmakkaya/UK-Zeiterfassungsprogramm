package com.example5.cemakkaya.database.service;

import com.example5.cemakkaya.database.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    public final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
}
