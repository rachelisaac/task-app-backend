package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // פונקציה לרישום משתמש חדש
    public User registerUser(UserDto userDto) {
        if (userRepo.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = UserMapper.toEntity(userDto);
        return userRepo.save(user);
    }

    // פונקציה לאימות משתמש
    public User authenticateUser(UserDto userDto) {
        User user = userRepo.findByUsername(userDto.getUsername());
        if (user != null && user.getPassword().equals(userDto.getPassword())) {
            return user;
        }
        throw new IllegalArgumentException("Invalid username or password");
    }



    // פונקציה להחזרת מידע על משתמש
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // פונקציה למחוק משתמש
    public void deleteUser(String username) {
        if (!userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepo.deleteById(username);
    }

    // פונקציה שמחזירה את כל המשתמשים (למנהל בלבד)
    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
