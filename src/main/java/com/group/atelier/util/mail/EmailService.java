package com.group.atelier.util.mail;

import com.group.atelier.security.TokenRepository;
import com.group.atelier.security.dto.RegistrationRequest;
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
    private final TokenRepository tokenRepository;

    @Value("${spring.custom.frontend.domain}")
    private String frontendDomain;
    @Value("${spring.custom.frontend.protocol}")
    private String protocol;
    @Value("${spring.mail.username}")
    private String mailUsername;

    private static final String REGISTRATION_CONFIRMATION_TEMPLATE_PATH = "/email_templates/registration-confirmation.txt";
    private static final String PASSWORD_RECOVERY_TEMPLATE_PATH = "/email_templates/password-recovery.txt";

    public void sendRegistrationConfirmationEmail(RegistrationRequest request, String registrationToken) throws IOException {
        String contentTemplate = this.getContentTemplate(REGISTRATION_CONFIRMATION_TEMPLATE_PATH);
        String url = protocol + "://" + frontendDomain + "/activate/" + registrationToken;
        String content = String.format(contentTemplate, request.firstName(), url);
        SimpleMailMessage message = prepareSimpleMessage(request.email(), "Registration Confirmation", content);
        mailSender.send(message);
    }

    public void sendPasswordRecoveryEmail(String emailAddress, String token) throws IOException {
        String contentTemplate = this.getContentTemplate(PASSWORD_RECOVERY_TEMPLATE_PATH);
        String url = protocol + "://" + frontendDomain + "/recover-password/" + token;
        String content = String.format(contentTemplate, url);
        SimpleMailMessage message = prepareSimpleMessage(emailAddress, "Password Recovery", content);
        mailSender.send(message);
    }

    private SimpleMailMessage prepareSimpleMessage(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("CloTech <" + mailUsername + ">");
        message.setSubject(subject);
        message.setText(content);
        return message;
    }

    private String getContentTemplate(String path) throws IOException {
        var inputStream = getClass().getResourceAsStream(path);
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
