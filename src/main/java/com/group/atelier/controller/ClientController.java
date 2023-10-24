package com.group.atelier.controller;

import com.group.atelier.dto.request.ClientRegistrationRequest;
import com.group.atelier.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerClient(@RequestBody ClientRegistrationRequest request) {
        clientService.registerClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
