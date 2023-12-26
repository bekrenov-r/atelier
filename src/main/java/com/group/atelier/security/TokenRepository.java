package com.group.atelier.security;

import com.group.atelier.model.entity.Token;
import com.group.atelier.model.entity.User;
import com.group.atelier.model.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUser(User user);
    Optional<Token> findByValueAndType(String token, TokenType type);
    void deleteByValue(String value);
}
