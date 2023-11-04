package com.group.atelier.util;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.exception.ApplicationExceptionReason;
import com.group.atelier.model.entity.User;
import com.group.atelier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class LoggedUserUtil {
    private final UserRepository userRepository;

    public User getUser(){
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, username));
    }
}
