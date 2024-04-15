package com.example5.cemakkaya.database;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Time morning_in;

    @Column(nullable = true)
    private Time morning_out;

    @Column(nullable = true)
    private Time afternoon_in;

    @Column(nullable = true)
    private Time afternoon_out;

    public History() {

    }

    public History(String usernam, LocalDate date, Time morning_in, Time morning_out, Time afternoon_in, Time afternoon_out) {
        this.username = usernam;
        this.date = date;
        this.morning_in = morning_in;
        this.morning_out = morning_out;
        this.afternoon_in = afternoon_in;
        this.afternoon_out = afternoon_out;
    }

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker user;

    @ManyToMany
    @JoinColumn(name = "activity_id", nullable = false)
    private List<Activity> activities;
}