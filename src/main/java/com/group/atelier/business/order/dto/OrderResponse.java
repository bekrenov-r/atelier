package com.group.atelier.business.order.dto;

import com.group.atelier.business.coatmodel.dto.CoatModelResponse;
import com.group.atelier.business.patterndata.dto.PatternDataResponse;
import com.group.atelier.business.productmetrics.dto.ProductMetricsDTO;
import com.group.atelier.model.enums.OrderStatus;

import java.time.ZonedDateTime;

public record OrderResponse(
    Long id,
    ZonedDateTime createdAt,
    OrderStatus status,
    CoatModelResponse coatModel,
    PatternDataResponse patternData,
    ProductMetricsDTO productMetrics
) { }
