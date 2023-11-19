package com.group.atelier.security;

import com.group.atelier.model.entity.RegistrationToken;
import com.group.atelier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {
    RegistrationToken findByUser(User user);
    Optional<RegistrationToken> findByToken(String token);
}
