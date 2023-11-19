package com.group.atelier.security.user;

import com.group.atelier.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable String token){
        return ResponseEntity.ok(userService.activateUser(token));
    }
}
