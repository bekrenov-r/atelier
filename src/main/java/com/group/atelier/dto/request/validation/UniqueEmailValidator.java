package com.group.atelier.dto.request.validation;

import com.group.atelier.dto.request.ClientRegistrationRequest;
import com.group.atelier.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator
        implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(email);
    }
}
