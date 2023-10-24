package com.group.atelier.dto.request;

public record ClientRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password
) implements UserRegistrationRequest {}
