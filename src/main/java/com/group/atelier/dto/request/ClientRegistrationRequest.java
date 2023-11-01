package com.group.atelier.dto.request;

import com.group.atelier.dto.request.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRegistrationRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank @Email
        @UniqueEmail(message = "User with given email address already exists")
        String email,
        @NotBlank
        String password
) implements UserRegistrationRequest {}
