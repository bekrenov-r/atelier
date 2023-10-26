package com.group.atelier.repository;

import com.group.atelier.model.RegistrationToken;
import com.group.atelier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {
    RegistrationToken findByUser(User user);
    Optional<RegistrationToken> findByToken(String token);
}
