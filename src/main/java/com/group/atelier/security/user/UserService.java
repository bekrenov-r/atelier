package com.group.atelier.security.user;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.RegistrationToken;
import com.group.atelier.model.entity.User;
import com.group.atelier.security.JwtProvider;
import com.group.atelier.security.RegistrationTokenRepository;
import com.group.atelier.security.Role;
import com.group.atelier.security.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.group.atelier.exception.ApplicationExceptionReason.REGISTRATION_TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final JwtProvider jwtProvider;

    public User createUser(UserRegistrationRequest request, Set<Role> roles) {
        User user = User.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roles)
                .active(false)
                .build();
        if(user.hasRole(Role.EMPLOYEE))
            user.setActive(true);
        User savedUser = userRepository.save(user);
        createRegistrationToken(savedUser);
        return savedUser;
    }

    private void createRegistrationToken(User user){
        RegistrationToken token = RegistrationToken.builder()
                .token(RandomStringUtils.random(20, true, true))
                .user(user)
                .build();
        registrationTokenRepository.save(token);
    }


    public String activateUser(String token) {
        var user = registrationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ApplicationException(REGISTRATION_TOKEN_NOT_FOUND, token))
                .getUser();
        user.setActive(true);
        userRepository.save(user);
        return jwtProvider.generateToken(user);
    }
}
