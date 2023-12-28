package com.group.atelier.client;

import com.group.atelier.client.dto.ClientRegistrationRequest;
import com.group.atelier.client.dto.ClientResponse;
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
    public ResponseEntity<ClientResponse> registerClient(@RequestBody @Valid ClientRegistrationRequest request) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.registerClient(request));
    }

    @PostMapping("/register/resend-email")
    public ResponseEntity<Void> resendRegistrationConfirmationEmail(@RequestBody ClientRegistrationRequest request) throws IOException {
        clientService.resendEmailRegistrationConfirmationEmail(request);
        return ResponseEntity.ok().build();
    }
}
