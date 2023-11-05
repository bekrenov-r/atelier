package com.group.atelier.dto.response;

import java.time.LocalDateTime;

public record OrderResponse(
    Long id,
    LocalDateTime createdAt,
    CoatModelResponse coatModel,
    PatternDataResponse patternData
) { }
