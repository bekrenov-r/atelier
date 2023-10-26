package com.group.atelier.controller;

import com.group.atelier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> activateUser(@PathVariable String token){
        userService.activateUser(token);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
