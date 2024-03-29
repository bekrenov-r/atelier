package com.group.atelier.security.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String CLIENT_REGISTRATION_MATCHER = "/clients/register";

    @Value("${spring.custom.security.permitted-matchers}")
    private String[] permittedMatchers;

    private final JwtAuthenticator jwtAuthenticator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        jwtAuthenticator.authenticateWithJwt(request);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        if(isClientRegistrationRequest(request))
            return shouldNotFilterClientRegistrationRequest(request);

        return Arrays.stream(permittedMatchers)
                .map(this::parseMatcher)
                .anyMatch(matcher -> matcher.matches(request));
    }

    private AntPathRequestMatcher parseMatcher(String matcher){
        String[] arr = matcher.split(" ");
        return new AntPathRequestMatcher(arr[1], arr[0]);
    }

    private boolean isClientRegistrationRequest(HttpServletRequest request){
        return new AntPathRequestMatcher(CLIENT_REGISTRATION_MATCHER).matches(request);
    }

    private boolean shouldNotFilterClientRegistrationRequest(HttpServletRequest request){
        return request.getHeader(HttpHeaders.AUTHORIZATION) == null;
    }
}
