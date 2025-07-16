package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
@Data

public class TaskDto {
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    private String username;
}
