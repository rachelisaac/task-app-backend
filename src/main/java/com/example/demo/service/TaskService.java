package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.repository.TaskRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    // פונקציה לקבלת משימות של משתמש
    public List<TaskDto> getTasksByUser(String username) {
        return taskRepo.findByUserUsername(username)
                .stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    // פונקציה להוספת משימה חדשה
    public Task createTask(TaskDto taskDto) {
        User user = userRepo.findByUsername(taskDto.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Task task = TaskMapper.toEntity(taskDto, user);
        return taskRepo.save(task);
    }

    // פונקציה לעדכון מצב משימה
    public Task updateTaskStatus(Long taskId, boolean completed) {
        Optional<Task> taskOptional = taskRepo.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new IllegalArgumentException("Task not found");
        }
        Task task = taskOptional.get();
        task.setCompleted(completed);
        return taskRepo.save(task);
    }

    // פונקציה למחיקת משימה
    public void deleteTask(Long taskId) {
        if (!taskRepo.existsById(taskId)) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepo.deleteById(taskId);
    }
}
