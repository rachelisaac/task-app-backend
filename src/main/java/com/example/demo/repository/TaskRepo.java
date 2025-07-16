package com.example.demo.repository;

import com.example.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByUserUsername(String username);

    List<Task> findByCompleted(boolean completed);

    boolean existsByDescriptionAndUserUsername(String description, String username);
}

