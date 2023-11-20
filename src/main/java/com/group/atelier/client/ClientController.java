package com.group.atelier.client;

import com.group.atelier.model.dto.request.ClientRegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
