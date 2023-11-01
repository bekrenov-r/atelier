package com.group.atelier.controller;

import com.group.atelier.dto.request.ClientRegistrationRequest;
import com.group.atelier.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerClient(@RequestBody @Valid ClientRegistrationRequest request) throws IOException {
        clientService.registerClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
