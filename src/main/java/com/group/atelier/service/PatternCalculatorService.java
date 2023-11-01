package com.group.atelier.service;

import com.group.atelier.dto.response.PatternDataResponse;
import com.group.atelier.dto.mapper.PatternDataMapper;
import com.group.atelier.dto.request.ProductMetricsRequest;
import com.group.atelier.model.entity.PatternData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatternCalculatorService {
    private final PatternCalculator patternCalculator;
    private final PatternDataMapper patternDataMapper;

    public PatternDataResponse calculatePatternData(ProductMetricsRequest request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        return patternDataMapper.entityToResponse(patternData);
    }

    public PatternDataResponse calculatePatternDataAndSave(ProductMetricsRequest request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        return null;
    }
}
