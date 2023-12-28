package com.group.atelier.client.dto;

import com.group.atelier.security.dto.RegistrationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRegistrationRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank @Email
        String email,
        String password
) implements RegistrationRequest {}
