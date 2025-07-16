package com.example.demo.mapper;

import com.example.demo.dto.TaskDto;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;

public class TaskMapper {

    // ממיר מ־DTO ל־Entity
    public static Task toEntity(TaskDto dto, User user) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setCompleted(dto.isCompleted());
        task.setUser(user);
        return task;
    }

    // ממיר מ־Entity ל־DTO
    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setCompleted(task.isCompleted());
        dto.setUsername(task.getUser().getUsername());
        return dto;
    }
}

