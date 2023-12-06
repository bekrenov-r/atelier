package com.group.atelier.model.dto;

import com.group.atelier.model.dto.request.ClientMetricsRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductMetricsDTO(
        @NotNull
        ClientMetricsRequest clientMetrics,
        // Пг
        @NotNull @Positive
        Double increaseToWidthByChestLine,
        // Пг.пр
        @NotNull @Positive
        Double increaseToArmholeDepth,
        // Пш.г
        @NotNull @Positive
        Double increaseToNeckBack
) { }
