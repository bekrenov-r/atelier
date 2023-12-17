package com.group.atelier.security.auth;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.exception.InvalidAuthHeaderException;
import com.group.atelier.security.JwtProvider;
import com.group.atelier.security.user.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class JwtAuthenticator {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private static final String BEARER_PREFIX = "Bearer ";

    public void authenticateWithJwt(HttpServletRequest request){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith(BEARER_PREFIX))
            throw new InvalidAuthHeaderException();
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        if(jwtProvider.validateToken(jwt)){
            String username = jwtProvider.getUsername(jwt);
            UserDetails user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, username));
            var auth = UsernamePasswordAuthenticationToken.authenticated(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else throw new JwtException("Invalid JWT token");
    }
}
