package com.group.atelier.model.dto;

import com.group.atelier.model.dto.request.ClientMetricsRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


public record ProductMetricsDTO(
        @NotNull @Valid
        ClientMetricsRequest clientMetrics,
        // Пг
        @NotNull @DecimalMin("2") @DecimalMax("25")
        Double increaseToWidthByChestLine,
        // Пг.пр
        @NotNull @DecimalMin("0") @DecimalMax("5")
        Double increaseToArmholeDepth,
        // Пш.г
        @NotNull @DecimalMin("0") @DecimalMax("5")
        Double increaseToNeckBack
) { }
