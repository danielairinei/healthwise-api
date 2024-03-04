package com.pula.dto.request;

import com.pula.util.enums.UserRole;

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
