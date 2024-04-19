package com.example5.cemakkaya.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;
}