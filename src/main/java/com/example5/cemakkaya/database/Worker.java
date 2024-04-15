package com.example5.cemakkaya.database;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prename;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private Long timeBalance;

    @Column(nullable = false)
    private String historyId;

    public Worker() {

    }

    public Worker(String prename, String name, String username, Long timeBalance, String historyId) {
        this.prename = prename;
        this.name = name;
        this.username = username;
        this.timeBalance = timeBalance;
        this.historyId = historyId;
    }

    @OneToMany
    @JoinColumn(name = "history_id", nullable = false)
    private List<History> history;

}