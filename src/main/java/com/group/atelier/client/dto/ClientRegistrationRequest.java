package com.group.atelier.client.dto;

import com.group.atelier.client.dto.validation.UniqueEmail;
import com.group.atelier.security.dto.UserRegistrationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRegistrationRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank @Email
        @UniqueEmail(message = "User with given email address already exists")
        String email,
        String password
) implements UserRegistrationRequest {}
