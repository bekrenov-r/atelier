package com.group.atelier.model.dto;

import com.group.atelier.model.dto.request.ClientMetricsRequest;
import jakarta.validation.constraints.NotNull;


public record ProductMetricsDTO(
        @NotNull
        ClientMetricsRequest clientMetrics,
        // Пг
        Double increaseToWidthByChestLine,
        // Пг.пр
        Double increaseToArmholeDepth,
        // Пш.г
        Double increaseToNeckBack
) { }
