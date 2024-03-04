package com.pula.dto.request;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
}
