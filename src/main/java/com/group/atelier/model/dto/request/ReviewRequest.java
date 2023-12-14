package com.group.atelier.model.dto.request;

public record ReviewRequest(
        Long coatModelId,
        String content,
        Short rating
) { }
