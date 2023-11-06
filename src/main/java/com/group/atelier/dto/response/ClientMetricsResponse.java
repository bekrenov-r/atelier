package com.group.atelier.dto.response;

public record ClientMetricsResponse(
        Long clientId,
        Double neckSemiCircumference,
        Double chestSemiCircumference1,
        Double chestSemiCircumference2,
        Double chestSemiCircumference3,
        Double waistSemiCircumference,
        Double shoulderWidth,
        Double chestHeight,
        Double chestHeight1,
        Double backArmholeHeight,
        Double backLengthTillWaist,
        Double shoulderHeightSidelong,
        Double chestWidth,
        Double chestCenter,
        Double backWidth,
        Double waistLengthFront,
        Double neckBaseToFrontWaistLineDistance
) { }
