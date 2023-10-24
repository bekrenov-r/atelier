package com.group.atelier.service;

import com.group.atelier.dto.request.UserRegistrationRequest;
import com.group.atelier.model.Role;
import com.group.atelier.model.User;
import com.group.atelier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRegistrationRequest request, Set<Role> roles) {
        User user = User.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roles)
                .build();
        return userRepository.save(user);
    }
}
