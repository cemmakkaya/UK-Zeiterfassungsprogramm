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

    public Activity(String activity) {
        this.activity = activity;
    }

}