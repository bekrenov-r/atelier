package com.group.atelier.security.user;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Token;
import com.group.atelier.model.entity.User;
import com.group.atelier.model.enums.TokenType;
import com.group.atelier.security.JwtProvider;
import com.group.atelier.security.Role;
import com.group.atelier.security.TokenRepository;
import com.group.atelier.security.dto.RegistrationRequest;
import com.group.atelier.util.mail.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;

    public User createUser(RegistrationRequest request, Set<Role> roles) throws IOException {
        User user = User.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roles)
                .active(false)
                .build();
        if(user.hasRole(Role.EMPLOYEE))
            user.setActive(true);
        User savedUser = userRepository.save(user);
        var registrationToken = createRegistrationToken(savedUser);
        emailService.sendRegistrationConfirmationEmail(request, registrationToken.getValue());
        return savedUser;
    }

    private Token createRegistrationToken(User user){
        Token token = Token.builder()
                .value(RandomStringUtils.random(20, true, true))
                .type(TokenType.REGISTRATION)
                .user(user)
                .build();
        return tokenRepository.save(token);
    }

    @Transactional
    public String activateUser(String token) {
        var user = tokenRepository.findByValueAndType(token, TokenType.REGISTRATION)
                .orElseThrow(() -> new ApplicationException(REGISTRATION_TOKEN_NOT_FOUND, token))
                .getUser();
        user.setActive(true);
        userRepository.save(user);
        tokenRepository.deleteByValue(token);
        return jwtProvider.generateToken(user);
    }

    public void sendEmailForPasswordRecovery(String email) throws IOException {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, email));
        Token token = Token.builder()
                .value(RandomStringUtils.random(20, true, true))
                .type(TokenType.PASSWORD_RECOVERY)
                .user(user)
                .build();
        tokenRepository.save(token);
        emailService.sendPasswordRecoveryEmail(email, token.getValue());
    }

    @Transactional
    public String recoverPassword(String token, String newPassword) {
        var user = tokenRepository.findByValueAndType(token, TokenType.PASSWORD_RECOVERY)
                .orElseThrow(() -> new ApplicationException(PASSWORD_RECOVERY_TOKEN_NOT_FOUND, token))
                .getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        tokenRepository.deleteByValue(token);
        return jwtProvider.generateToken(user);
    }
}
