package com.group.atelier.security;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.exception.InvalidAuthHeaderException;
import com.group.atelier.security.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

import static com.group.atelier.exception.ApplicationExceptionReason.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    @Value("${spring.custom.security.permitted-matchers}")
    private String[] permittedMatchers;

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith(BEARER_PREFIX))
            throw new InvalidAuthHeaderException();
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        if(jwtProvider.validateToken(jwt)){
            String username = jwtProvider.getUsername(jwt);
            UserDetails user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, username));
            var auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            auth.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return Arrays.stream(permittedMatchers)
                .map(AntPathRequestMatcher::new)
                .anyMatch(matcher -> matcher.matches(request));
    }
}
