package com.airline.userservice.mapper;

import com.airline.commonlib.dto.UserDTO;
import com.airline.commonlib.enums.UserRole;
import com.airline.commonlib.payload.request.auth.RegisterRequest;
import com.airline.userservice.entity.User;
import lombok.Getter;

@Getter
public class UserMapper {

    public static User toEntity(RegisterRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .lastLogin(request.getLastLogin())
                .role(request.getRole())
                .build();
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .build();
    }
}
