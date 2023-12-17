package com.group.atelier.business.order.dto;

import com.group.atelier.business.productmetrics.dto.ProductMetricsDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @Positive
        Long clientId,
        @Positive @NotNull
        Long coatModelId,
        @NotNull
        ProductMetricsDTO productMetrics
) { }
