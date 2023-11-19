package com.group.atelier.model.dto.response;

import com.group.atelier.model.dto.ProductMetricsDTO;
import com.group.atelier.model.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponse(
    Long id,
    LocalDateTime createdAt,
    OrderStatus status,
    CoatModelResponse coatModel,
    PatternDataResponse patternData,
    ProductMetricsDTO productMetrics
) { }
