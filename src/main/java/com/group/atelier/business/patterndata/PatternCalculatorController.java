package com.group.atelier.business.patterndata;

import com.group.atelier.business.patterndata.dto.PatternDataResponse;
import com.group.atelier.business.productmetrics.dto.ProductMetricsDTO;
import jakarta.validation.Valid;
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
    public ResponseEntity<PatternDataResponse> calculatePatternData(@RequestBody @Valid ProductMetricsDTO request){
        return ResponseEntity.ok(patternCalculatorService.calculatePatternData(request));
    }
}
