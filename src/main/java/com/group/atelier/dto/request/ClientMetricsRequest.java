package com.group.atelier.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClientMetricsRequest(
        @NotNull @Positive
        Double neckSemiCircumference,
        @NotNull @Positive
        Double chestSemiCircumference1,
        @NotNull @Positive
        Double chestSemiCircumference2,
        @NotNull @Positive
        Double chestSemiCircumference3,
        @NotNull @Positive
        Double waistSemiCircumference,
        @NotNull @Positive
        Double shoulderWidth,
        @NotNull @Positive
        Double chestHeight,
        @NotNull @Positive
        Double chestHeight1,
        @NotNull @Positive
        Double backArmholeHeight,
        @NotNull @Positive
        Double backLengthTillWaist,
        @NotNull @Positive
        Double shoulderHeightSidelong,
        @NotNull @Positive
        Double chestWidth,
        @NotNull @Positive
        Double chestCenter,
        @NotNull @Positive
        Double backWidth,
        @NotNull @Positive
        Double waistLengthFront,
        @NotNull @Positive
        Double neckBaseToFrontWaistLineDistance
) { }
