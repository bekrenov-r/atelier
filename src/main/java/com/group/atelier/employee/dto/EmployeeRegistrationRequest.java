package com.group.atelier.employee.dto;

import com.group.atelier.security.dto.RegistrationRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRegistrationRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank @Email
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String password
) implements RegistrationRequest { }
