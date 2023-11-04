package com.group.atelier.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @Positive @NotNull
        Long coatModelId,
        @NotNull
        ProductMetricsRequest productMetrics
) { }
