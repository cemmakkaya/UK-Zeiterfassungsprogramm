package com.example5.cemakkaya.database;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String activity;

    public Activity() {

    }

    public Activity(String usernam) {
        this.activity = activity;
    }

    @ManyToMany
    @JoinColumn(name = "history_id", nullable = false)
    private List<History> history;

}