package com.group.atelier.service;

import com.group.atelier.business.PatternCalculator;
import com.group.atelier.dto.response.PatternDataResponse;
import com.group.atelier.dto.mapper.PatternDataMapper;
import com.group.atelier.dto.request.ProductMetricsRequest;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.PatternData;
import com.group.atelier.repository.ClientRepository;
import com.group.atelier.repository.PatternDataRepository;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatternCalculatorService {
    private final PatternCalculator patternCalculator;
    private final PatternDataMapper patternDataMapper;
    private final PatternDataRepository patternDataRepository;
    private final CurrentUserUtil currentUserUtil;
    private final ClientRepository clientRepository;

    public PatternDataResponse calculatePatternData(ProductMetricsRequest request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        return patternDataMapper.entityToResponse(patternData);
    }

    public PatternData calculatePatternDataAndSave(ProductMetricsRequest request) {
        PatternData patternData = patternCalculator.doCalculate(request);
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        patternData.setClient(client);
        return patternDataRepository.save(patternData);
    }
}
