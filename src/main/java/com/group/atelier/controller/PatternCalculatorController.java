package com.group.atelier.controller;

import com.group.atelier.dto.request.ProductMetricsRequest;
import com.group.atelier.dto.response.PatternDataResponse;
import com.group.atelier.service.PatternCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patterns")
@RequiredArgsConstructor
public class PatternCalculatorController {
    private final PatternCalculatorService patternCalculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<PatternDataResponse> calculatePatternData(@RequestBody ProductMetricsRequest request){
        return ResponseEntity.ok(patternCalculatorService.calculatePatternData(request));
    }

    @PostMapping("/calculate/save")
    public ResponseEntity<PatternDataResponse> calculatePatternDataAndSave(@RequestBody ProductMetricsRequest request){
        return ResponseEntity.ok(patternCalculatorService.calculatePatternDataAndSave(request));
    }
}
