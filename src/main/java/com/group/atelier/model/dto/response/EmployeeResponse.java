package com.group.atelier.model.dto.response;

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
