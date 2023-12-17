package com.group.atelier.client.dto;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) { }
