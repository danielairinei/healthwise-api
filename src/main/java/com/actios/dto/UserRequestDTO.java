package com.actios.dto;

import com.actios.util.enums.UserRole;

public record UserRequestDTO(
        String username,
        String password,
        String email,
        String phoneNumber,
        String firstName,
        String lastName,
        UserRole role
) {
}
