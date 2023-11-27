package com.group.atelier.util.mail;

import com.group.atelier.model.entity.Client;
import com.group.atelier.security.RegistrationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        var inputStream = getClass().getResourceAsStream(CONTENT_TEMPLATE_FILE_PATH);
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
