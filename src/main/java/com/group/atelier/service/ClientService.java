package com.group.atelier.service;

import com.group.atelier.dto.request.ClientRegistrationRequest;
import com.group.atelier.dto.request.UserRegistrationRequest;
import com.group.atelier.model.Client;
import com.group.atelier.model.Role;
import com.group.atelier.model.User;
import com.group.atelier.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final EmailService emailService;

    public void registerClient(ClientRegistrationRequest request) throws IOException {
        User user = userService.createUser(request, Set.of(Role.CLIENT));
        Client client = Client.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .user(user)
                .build();
        clientRepository.save(client);
        emailService.sendRegistrationConfirmationEmail(client);
    }
}
