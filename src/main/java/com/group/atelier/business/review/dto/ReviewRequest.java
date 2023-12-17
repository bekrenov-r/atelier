package com.group.atelier.business.review.dto;

public record ReviewRequest(
        Long coatModelId,
        String content,
        Short rating
) { }
