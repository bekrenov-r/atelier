package com.group.atelier.employee.dto;

import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDateTime registeredAt
) {
}
