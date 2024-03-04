package com.actio.dto.request;

public record AuthenticationRequestDTO(
        String email,
        String password
) {
}
