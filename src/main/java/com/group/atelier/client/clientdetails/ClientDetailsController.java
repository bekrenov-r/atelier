package com.group.atelier.client.clientdetails;

import com.group.atelier.client.clientdetails.dto.ClientDetailsRequest;
import com.group.atelier.client.clientdetails.dto.ClientDetailsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/details")
@RequiredArgsConstructor
@Secured("CLIENT")
public class ClientDetailsController {
    private final ClientDetailsService clientDetailsService;

    @GetMapping
    public ResponseEntity<ClientDetailsResponse> getClientDetailsByCurrentUser(){
        return ResponseEntity.ok(clientDetailsService.getClientDetailsByCurrentUser());
    }

    @PostMapping
    public ResponseEntity<Void> createClientDetailsForCurrentUser(@RequestBody @Valid ClientDetailsRequest request){
        clientDetailsService.createClientDetailsForCurrentUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping
    public ResponseEntity<ClientDetailsResponse> updateClientDetailsForCurrentUser(@RequestBody @Valid ClientDetailsRequest request){
        return ResponseEntity.ok(clientDetailsService.updateClientDetailsForCurrentUser(request));
    }
}
