package com.group.atelier.model.dto.response;

public record ReviewResponse(
        String clientFullName,
        String content,
        Short rating
) { }
