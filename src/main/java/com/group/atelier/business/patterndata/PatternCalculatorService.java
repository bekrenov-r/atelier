package com.group.atelier.business.patterndata;

import com.group.atelier.model.dto.ProductMetricsDTO;
import com.group.atelier.business.patterndata.dto.PatternDataMapper;
import com.group.atelier.model.dto.response.PatternDataResponse;
import com.group.atelier.model.entity.PatternData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatternCalculatorService {
    private final PatternCalculator patternCalculator;
    private final PatternDataMapper patternDataMapper;
    private final PatternDataRepository patternDataRepository;

    public PatternDataResponse calculatePatternData(ProductMetricsDTO request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        return patternDataMapper.entityToResponse(patternData);
    }

    public PatternData calculatePatternDataAndSave(ProductMetricsDTO request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        return patternDataRepository.save(patternData);
    }
}
