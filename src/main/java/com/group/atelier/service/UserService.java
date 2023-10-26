package com.group.atelier.service;

import com.group.atelier.dto.request.UserRegistrationRequest;
import com.group.atelier.model.RegistrationToken;
import com.group.atelier.model.Role;
import com.group.atelier.model.User;
import com.group.atelier.repository.RegistrationTokenRepository;
import com.group.atelier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationTokenRepository registrationTokenRepository;

    public User createUser(UserRegistrationRequest request, Set<Role> roles) {
        User user = User.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roles)
                .active(false)
                .build();
        User savedUser = userRepository.save(user);
        RegistrationToken token = RegistrationToken.builder()
                .token(RandomStringUtils.random(20, true, true))
                .user(savedUser)
                .build();
        registrationTokenRepository.save(token);
        return savedUser;
    }


    public void activateUser(String token) {
        var user = registrationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found")) // todo: use custom exception with reason
                .getUser();
        user.setActive(true);
        userRepository.save(user);
    }
}
