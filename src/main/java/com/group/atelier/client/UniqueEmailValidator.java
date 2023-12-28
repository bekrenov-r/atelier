package com.group.atelier.client;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.USER_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator{
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public void assertEmailIsUnique(String email) {
        if(userRepository.existsByUsername(email) || clientRepository.existsByEmail(email))
            throw new ApplicationException(USER_ALREADY_EXISTS, email);
    }
}
