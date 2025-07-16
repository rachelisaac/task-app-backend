package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.entities.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // החזרת כל המשימות של משתמש מסוים לפי שם משתמש
    @GetMapping("/user/{username}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable String username) {
        List<TaskDto> tasks = taskService.getTasksByUser(username);
        return ResponseEntity.ok(tasks);
    }

    // יצירת משימה חדשה
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // עדכון מצב של משימה (completed = true/false)
    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam boolean completed) {
        Task updatedTask = taskService.updateTaskStatus(taskId, completed);
        return ResponseEntity.ok(updatedTask);
    }

    // מחיקת משימה לפי ID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
