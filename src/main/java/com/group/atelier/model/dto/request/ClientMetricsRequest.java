package com.group.atelier.model.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ClientMetricsRequest(
        // Сш
        @NotNull @DecimalMin("13.5") @DecimalMax("21.5")
        Double neckSemiCircumference,
        // Сг1
        @NotNull @DecimalMin("33.2") @DecimalMax("53.2")
        Double chestSemiCircumference1,
        // Сг2
        @NotNull @DecimalMin("36.4") @DecimalMax("56.4")
        Double chestSemiCircumference2,
        // Сг3
        @NotNull @DecimalMin("34.0") @DecimalMax("54.0")
        Double chestSemiCircumference3,
        // Ст
        @NotNull @DecimalMin("23.8") @DecimalMax("43.8")
        Double waistSemiCircumference,
        // Шп
        @NotNull @DecimalMin("8.1") @DecimalMax("18.1")
        Double shoulderWidth,
        // Вг
        @NotNull @DecimalMin("16.0") @DecimalMax("36.0")
        Double chestHeight,
        // Вг1
        @NotNull @DecimalMin("23.7") @DecimalMax("43.7")
        Double chestHeight1,
        // Впрз
        @NotNull @DecimalMin("12.5") @DecimalMax("22.5")
        Double backArmholeHeight,
        // Дтс
        @NotNull @DecimalMin("30.1") @DecimalMax("50.1")
        Double backLengthTillWaist,
        // Впк
        @NotNull @DecimalMin("32.8") @DecimalMax("52.8")
        Double shoulderHeightSidelong,
        // Шг
        @NotNull @DecimalMin("11.5") @DecimalMax("21.5")
        Double chestWidth,
        // Цг
        @NotNull @DecimalMin("4.6") @DecimalMax("14.6")
        Double chestCenter,
        // Шс
        @NotNull @DecimalMin("9.3") @DecimalMax("25.3")
        Double backWidth,
        // Дтп
        @NotNull @DecimalMin("41.5") @DecimalMax("61.5")
        Double waistLengthFront,
        // Дтп1
        @NotNull @DecimalMin("33.0") @DecimalMax("53.0")
        Double neckBaseToFrontWaistLineDistance
) { }
