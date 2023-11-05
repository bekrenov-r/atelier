package com.group.atelier.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationExceptionHandlerFilter extends OncePerRequestFilter {
    private final CustomizedResponseEntityExceptionHandler exceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex){
            ErrorResponse errorResponse = exceptionHandler.handleAuthenticationException(ex).getBody();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(this.convertErrorResponseToString(errorResponse));
        }
    }

    private String convertErrorResponseToString(ErrorResponse errorResponse) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(errorResponse);
    }
}
