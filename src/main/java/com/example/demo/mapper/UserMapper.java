package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;

import java.util.ArrayList;

public class UserMapper {

    //  מ־UserDto ל־ entity
    public static User toEntity(UserDto dto) {
        User user = new User(dto.getUsername(),dto.getPassword());
        user.setRole("user");
        user.setTasks(new ArrayList<>());
        return user;
    }



    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
