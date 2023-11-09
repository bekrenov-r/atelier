package com.group.atelier.service;

import com.group.atelier.model.entity.Client;
import com.group.atelier.repository.RegistrationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Value("${spring.custom.frontend.domain}")
    private String frontendDomain;

    private static final String CONTENT_TEMPLATE_FILE_PATH = "/registration-confirmation-email-content.txt";

    public void sendRegistrationConfirmationEmail(Client client) throws IOException {
        String contentTemplate = this.getContentTemplate();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(client.getEmail());
        message.setSubject("Registration Confirmation");
        String registrationToken = registrationTokenRepository
                .findByUser(client.getUser())
                .getToken();
        String url = "http://" + frontendDomain + "/activate/" + registrationToken;
        message.setText(String.format(contentTemplate, client.getFirstName(), url));
        mailSender.send(message);
    }

    private String getContentTemplate() throws IOException {
        return new String(getClass().getResourceAsStream(CONTENT_TEMPLATE_FILE_PATH).readAllBytes());
    }
}
