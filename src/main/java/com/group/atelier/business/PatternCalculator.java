package com.group.atelier.business;

import com.group.atelier.dto.ProductMetricsDTO;
import com.group.atelier.model.entity.PatternData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PatternCalculator {
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public PatternData doCalculate(ProductMetricsDTO request){
        // Пт
        BigDecimal increaseToWidthByWaistLine = BigDecimal
                .valueOf(request.increaseToWidthByChestLine())
                .multiply(BigDecimal.valueOf(0.5))
                .setScale(1, ROUNDING_MODE);
        // Пс
        BigDecimal increaseToBackWidth = BigDecimal
                .valueOf(request.increaseToWidthByChestLine())
                .multiply(BigDecimal.valueOf(0.25))
                .setScale(1, ROUNDING_MODE);;
        // Пп
        BigDecimal increaseToFileWidth = BigDecimal
                .valueOf(request.increaseToWidthByChestLine())
                .multiply(BigDecimal.valueOf(0.25))
                .setScale(1, ROUNDING_MODE);;
        // Ппр
        BigDecimal increaseToArmholeWidth = increaseToWidthByWaistLine
                .subtract(increaseToBackWidth.add(increaseToFileWidth))
                .setScale(1, ROUNDING_MODE);

        // AA1
        BigDecimal basisGridWidth = BigDecimal
                .valueOf(request.clientMetrics().chestSemiCircumference2())
                .add(BigDecimal.valueOf(request.increaseToWidthByChestLine()))
                .setScale(1, ROUNDING_MODE);
        // AT
        BigDecimal basisGridLength = BigDecimal
                .valueOf(request.clientMetrics().backLengthTillWaist())
                .setScale(1, ROUNDING_MODE);
        // AГ
        BigDecimal armholeDepth = BigDecimal
                .valueOf(request.clientMetrics().backArmholeHeight())
                .add(BigDecimal.valueOf(request.increaseToArmholeDepth()))
                .setScale(1, ROUNDING_MODE);
        // Aa
        BigDecimal backWidth = BigDecimal
                .valueOf(request.clientMetrics().backWidth())
                .add(increaseToBackWidth)
                .setScale(1, ROUNDING_MODE);
        // A1a1
        BigDecimal fileWidth = BigDecimal
                .valueOf(request.clientMetrics().chestWidth())
                .add(
                        BigDecimal
                                .valueOf(request.clientMetrics().chestSemiCircumference2())
                                .subtract(BigDecimal.valueOf(request.clientMetrics().chestSemiCircumference1()))
                ).add(increaseToFileWidth)
                .setScale(1, ROUNDING_MODE);
        // aa1
        BigDecimal armholeWidth = basisGridWidth
                .subtract(backWidth)
                .subtract(fileWidth)
                .setScale(1, ROUNDING_MODE);
        // AA2
        BigDecimal backNeckWidth = BigDecimal
                .valueOf(request.clientMetrics().neckSemiCircumference())
                .divide(BigDecimal.valueOf(3), ROUNDING_MODE)
                .add(BigDecimal.valueOf(request.increaseToNeckBack()))
                .setScale(1, ROUNDING_MODE);
        // A2A3
        BigDecimal backNeckHeight = backNeckWidth
                .divide(BigDecimal.valueOf(3), ROUNDING_MODE)
                .add(BigDecimal.valueOf(0.3))
                .setScale(1, ROUNDING_MODE);
        // аП
        BigDecimal shoulderCutSlope = BigDecimal.valueOf(2);
        // А3П1
        BigDecimal shoulderCutEnd = BigDecimal.valueOf(request.clientMetrics().shoulderWidth());
        // Г2Г4
        BigDecimal sideSlopeTop = BigDecimal.ZERO; // todo: clarify
        // Г2П2; Г21
        BigDecimal backArmholeSlope = BigDecimal.ZERO; // todo: clarify
        // A1A4
        BigDecimal productBalance = BigDecimal
                .valueOf(request.clientMetrics().neckBaseToFrontWaistLineDistance())
                .subtract(BigDecimal.valueOf(request.clientMetrics().backLengthTillWaist()))
                .setScale(1, ROUNDING_MODE);
        // A4A5
        BigDecimal fileNeckWidth = backNeckWidth; // todo: clarify
        // A4A6
        BigDecimal fileNeckDepth = fileNeckWidth // todo: clarify
                .add(BigDecimal.valueOf(1))
                .divide(BigDecimal.valueOf(1.5), ROUNDING_MODE)
                .setScale(1, ROUNDING_MODE);

        BigDecimal chestDart = BigDecimal.ZERO; // todo: clarify
        BigDecimal shoulderSlope = BigDecimal.ZERO; // todo: clarify
        BigDecimal armhole = BigDecimal.ZERO; // todo: clarify
        // Рв.т.
        BigDecimal totalDartDeviationByWaistLine = BigDecimal
                .valueOf(request.clientMetrics().chestSemiCircumference2())
                .add(BigDecimal.valueOf(request.increaseToWidthByChestLine()))
                .subtract(
                        BigDecimal
                                .valueOf(request.clientMetrics().waistSemiCircumference())
                                .add(increaseToWidthByWaistLine)
                ).setScale(1, ROUNDING_MODE);
        // Рб.в
        BigDecimal sideDart = totalDartDeviationByWaistLine
                .divide(BigDecimal.valueOf(3), ROUNDING_MODE)
                .add(BigDecimal.valueOf(1))
                .setScale(1, ROUNDING_MODE);
        // Рв.п
        BigDecimal fileDart = totalDartDeviationByWaistLine
                .divide(BigDecimal.valueOf(3), ROUNDING_MODE)
                .subtract(BigDecimal.valueOf(1))
                .setScale(1, ROUNDING_MODE);
        // Рв.с
        BigDecimal backDart = totalDartDeviationByWaistLine
                .divide(BigDecimal.valueOf(3), ROUNDING_MODE)
                .setScale(1, ROUNDING_MODE);

        return PatternData.builder()
                .basisGridWidth(basisGridWidth.doubleValue())
                .basisGridLength(basisGridLength.doubleValue())
                .armholeDepth(armholeDepth.doubleValue())
                .backWidth(backWidth.doubleValue())
                .fileWidth(fileWidth.doubleValue())
                .armholeWidth(armholeWidth.doubleValue())
                .backNeckWidth(backNeckWidth.doubleValue())
                .backNeckHeight(backNeckHeight.doubleValue())
                .shoulderCutSlope(shoulderCutSlope.doubleValue())
                .shoulderCutEnd(shoulderCutEnd.doubleValue())
                .sideSlopeTop(sideSlopeTop.doubleValue())
                .backArmholeSlope(backArmholeSlope.doubleValue())
                .productBalance(productBalance.doubleValue())
                .fileNeckWidth(fileNeckWidth.doubleValue())
                .fileNeckDepth(fileNeckDepth.doubleValue())
                .chestDart(chestDart.doubleValue())
                .shoulderSlope(shoulderSlope.doubleValue())
                .armhole(armhole.doubleValue())
                .totalDartDeviationByWaistLine(totalDartDeviationByWaistLine.doubleValue())
                .sideDart(sideDart.doubleValue())
                .fileDart(fileDart.doubleValue())
                .backDart(backDart.doubleValue())
                .increaseToWidthByChestLine(request.increaseToWidthByChestLine())
                .increaseToWidthByWaistLine(increaseToWidthByWaistLine.doubleValue())
                .increaseToArmholeWidth(increaseToArmholeWidth.doubleValue())
                .increaseToArmholeDepth(request.increaseToArmholeDepth())
                .increaseToNeckBack(request.increaseToNeckBack())
                .increaseToBackWidth(increaseToBackWidth.doubleValue())
                .increaseToFileWidth(increaseToFileWidth.doubleValue())
                .build();
    }
}
