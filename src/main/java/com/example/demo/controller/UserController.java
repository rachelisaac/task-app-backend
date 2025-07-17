package com.example.demo.controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // רישום משתמש חדש
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User newUser = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // אימות משתמש
//    @PostMapping("/login")
//    public ResponseEntity<User> authenticateUser(@RequestBody UserDto userDto) {
//        User authenticatedUser = userService.authenticateUser(userDto);
//        return ResponseEntity.ok(authenticatedUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDto userDto) {
        User authenticatedUser = userService.authenticateUser(userDto);
        String token = jwtUtil.generateToken(authenticatedUser);  // הנפקת הטוקן
        return ResponseEntity.ok(token);
    }


    // החזרת פרטי משתמש לפי שם משתמש
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    // מחיקת משתמש לפי שם משתמש
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // החזרת כל המשתמשים (למנהל בלבד)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
