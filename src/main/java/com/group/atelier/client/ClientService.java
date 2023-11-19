package com.group.atelier.client;

import com.group.atelier.model.dto.request.ClientRegistrationRequest;
import com.group.atelier.model.entity.Client;
import com.group.atelier.security.Role;
import com.group.atelier.model.entity.User;
import com.group.atelier.security.user.UserService;
import com.group.atelier.util.mail.EmailService;
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
                .user(user)
                .build();
        clientRepository.save(client);
        emailService.sendRegistrationConfirmationEmail(client);
    }
}
