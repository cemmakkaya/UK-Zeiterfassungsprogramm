package com.example5.cemakkaya.database.repository;

import com.example5.cemakkaya.database.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository  extends JpaRepository<History, Long> {
}
