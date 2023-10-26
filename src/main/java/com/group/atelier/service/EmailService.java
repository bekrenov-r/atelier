package com.group.atelier.service;

import com.group.atelier.model.Client;
import com.group.atelier.repository.ClientRepository;
import com.group.atelier.repository.RegistrationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Value("${custom.frontend.domain}")
    private String frontendDomain;

    private static final String CONTENT_TEMPLATE_FILE_PATH = "src/main/resources/registration-confirmation-email-content.txt";

    public void sendRegistrationConfirmationEmail(Client client) throws IOException {
        String contentTemplate = this.getContentTemplate();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(client.getEmail());
        message.setSubject("Registration Confirmation");
        String registrationToken = registrationTokenRepository
                .findByUser(client.getUser())
                .getToken();
        String url = "https://" + frontendDomain + "/activate/" + registrationToken;
        message.setText(String.format(contentTemplate, client.getFirstName(), url));
        mailSender.send(message);
    }

    private String getContentTemplate() throws IOException {
        return new String(
                Files.readAllBytes(Paths.get(CONTENT_TEMPLATE_FILE_PATH))
        );
    }
}
