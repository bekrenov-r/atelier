package com.group.atelier.business.patterndata.dto;

public record PatternDataResponse(
        Double basisGridWidth,
        Double basisGridLength,
        Double armholeDepth,
        Double backWidth,
        Double fileWidth,
        Double armholeWidth,
        Double backNeckWidth,
        Double backNeckHeight,
        Double shoulderCutSlope,
        Double shoulderCutEnd,
        Double productBalance,
        Double fileNeckWidth,
        Double fileNeckDepth,
        Double chestDart,
        Double shoulderSlope,
        Double armhole,
        Double totalDartDeviationByWaistLine,
        Double sideDart,
        Double fileDart,
        Double backDart
) { }
