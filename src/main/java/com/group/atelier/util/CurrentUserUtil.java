package com.group.atelier.util;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.User;
import com.group.atelier.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.group.atelier.exception.ApplicationExceptionReason.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {
    private final UserRepository userRepository;

    private static final Object ANONYMOUS_PRINCIPAL = "anonymousUser";

    public User getCurrentUser(){
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, username));
    }

    public boolean hasLoggedUser(){
         Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(principal);
        return !principal.equals(ANONYMOUS_PRINCIPAL);
    }
}
