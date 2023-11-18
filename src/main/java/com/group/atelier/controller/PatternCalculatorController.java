package com.group.atelier.controller;

import com.group.atelier.dto.ProductMetricsDTO;
import com.group.atelier.dto.response.PatternDataResponse;
import com.group.atelier.service.PatternCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patterns")
@RequiredArgsConstructor
public class PatternCalculatorController {
    private final PatternCalculatorService patternCalculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<PatternDataResponse> calculatePatternData(@RequestBody ProductMetricsDTO request){
        return ResponseEntity.ok(patternCalculatorService.calculatePatternData(request));
    }
}
