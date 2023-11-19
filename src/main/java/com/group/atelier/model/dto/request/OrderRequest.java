package com.group.atelier.model.dto.request;

import com.group.atelier.model.dto.ProductMetricsDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @Positive @NotNull
        Long coatModelId,
        @NotNull
        ProductMetricsDTO productMetrics
) { }
