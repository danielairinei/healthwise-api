package com.pula.dto.response;

import com.pula.entity.Preference;
import com.pula.util.enums.UserRole;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        String phoneNumber,
        String firstName,
        String lastName,
        int itemsDonated,
        UserRole role,
        List<Preference> preferences
) {

}
