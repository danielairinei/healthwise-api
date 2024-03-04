package com.actios.dto.builder;

import com.actios.dto.UserDTO;
import com.actios.dto.UserRequestDTO;
import com.actios.entity.User;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getItemsDonated(),
                user.getRole(),
                user.getPreferences()
        );
    }

    public static User toUserEntity(UserRequestDTO userRequestDTO) {
        return new User(
                userRequestDTO.username(),
                userRequestDTO.password(),
                userRequestDTO.email(),
                userRequestDTO.phoneNumber(),
                userRequestDTO.firstName(),
                userRequestDTO.lastName(),
                userRequestDTO.role()
        );
    }
}
