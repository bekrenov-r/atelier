package com.group.atelier.security.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
