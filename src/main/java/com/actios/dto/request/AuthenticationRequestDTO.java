package com.actios.dto.request;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
}
