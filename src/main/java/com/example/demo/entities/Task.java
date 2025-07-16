package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString

@Entity
public class Task {

    @Id
    @GeneratedValue
    private long id;

    private String description;

    private LocalDate dueDate;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    @JsonBackReference
    private User user;

    public Task(String description, LocalDate dueDate, boolean completed, User user) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
        this.user = user;
    }
}
