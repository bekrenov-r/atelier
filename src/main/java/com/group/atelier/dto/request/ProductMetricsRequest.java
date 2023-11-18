package com.group.atelier.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProductMetricsRequest(
        @NotNull
        ClientMetricsRequest clientMetrics,
        // Пг
        Double increaseToWidthByChestLine,
        // Пг.пр
        Double increaseToArmholeDepth,
        // Пш.г
        Double increaseToNeckBack
) { }
