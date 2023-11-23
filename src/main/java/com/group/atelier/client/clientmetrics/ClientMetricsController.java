package com.group.atelier.client.clientmetrics;

import com.group.atelier.model.dto.request.ClientMetricsRequest;
import com.group.atelier.model.dto.response.ClientMetricsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/metrics")
@RequiredArgsConstructor
@Secured("CLIENT")
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
