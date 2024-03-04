package com.actio.dto.request;

import com.actio.util.enums.UserRole;

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
