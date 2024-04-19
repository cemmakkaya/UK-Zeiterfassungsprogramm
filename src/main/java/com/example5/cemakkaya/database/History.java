package com.example5.cemakkaya.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime morning_in;

    @Column(nullable = true)
    private LocalDateTime morning_out;

    @Column(nullable = true)
    private LocalDateTime afternoon_in;

    @Column(nullable = true)
    private LocalDateTime afternoon_out;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    @JsonIgnore
    private Worker user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    @JsonIgnore
    private Activity activity;

    public History(Long id, String username, LocalDateTime morningIn, LocalDateTime morningOut, LocalDateTime afternoonIn) {
    }
}