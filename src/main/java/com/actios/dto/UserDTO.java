package com.actios.dto;

import com.actios.entity.Preference;
import com.actios.util.enums.UserRole;

import java.util.List;
import java.util.UUID;

public record UserDTO(
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
