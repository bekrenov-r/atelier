package com.group.atelier.client;

import com.group.atelier.model.dto.request.ClientRegistrationRequest;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.User;
import com.group.atelier.security.Role;
import com.group.atelier.security.user.UserService;
import com.group.atelier.util.CurrentUserUtil;
import com.group.atelier.util.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final CurrentUserUtil currentUserUtil;

    public void registerClient(ClientRegistrationRequest request) throws IOException {
        Client client = Client.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        if(shouldCreateUserForClient()){
            User user = userService.createUser(request, Set.of(Role.CLIENT));
            client.setUser(user);
            emailService.sendRegistrationConfirmationEmail(client);
        }

        clientRepository.save(client);
    }

    private boolean shouldCreateUserForClient(){
        System.out.println(currentUserUtil.hasLoggedUser());
        if(currentUserUtil.hasLoggedUser()){
            if(currentUserUtil.getCurrentUser().hasRole(Role.EMPLOYEE))
                return false;
            else
                throw new AuthorizationServiceException("You don't have authority required for this action");
        }
        return true;
    }
}
