package com.group.atelier.dto.response;

import com.group.atelier.dto.ProductMetricsDTO;

import java.time.LocalDateTime;

public record OrderResponse(
    Long id,
    LocalDateTime createdAt,
    CoatModelResponse coatModel,
    PatternDataResponse patternData,
    ProductMetricsDTO productMetrics
) { }
