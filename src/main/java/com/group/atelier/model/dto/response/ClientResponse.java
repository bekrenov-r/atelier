package com.group.atelier.model.dto.response;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) { }
