package com.group.atelier.controller;

import com.group.atelier.dto.request.ClientMetricsRequest;
import com.group.atelier.dto.response.ClientMetricsResponse;
import com.group.atelier.service.ClientMetricsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/metrics")
@RequiredArgsConstructor
public class ClientMetricsController {
    private final ClientMetricsService clientMetricsService;

    @GetMapping
    public ResponseEntity<ClientMetricsResponse> getClientMetricsByCurrentUser(){
        return ResponseEntity.ok(clientMetricsService.getClientMetricsByCurrentUser());
    }

    @PostMapping
    public ResponseEntity<Void> createClientMetricsForCurrentUser(
            @RequestBody @Valid ClientMetricsRequest request
    ){
        clientMetricsService.createClientMetricsForCurrentUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping
    public ResponseEntity<ClientMetricsResponse> updateClientMetricsForCurrentUser(
            @RequestBody @Valid ClientMetricsRequest request
    ){
        return ResponseEntity.ok(clientMetricsService.updateClientMetricsForCurrentUser(request));
    }
}
