package com.group.atelier.model.dto;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) { }
