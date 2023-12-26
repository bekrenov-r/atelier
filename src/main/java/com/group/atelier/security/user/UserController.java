package com.group.atelier.security.user;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable String token){
        return ResponseEntity.ok(userService.activateUser(token));
    }

    @GetMapping("/recover-password/send-email")
    public ResponseEntity<Void> sendEmailForPasswordRecovery(@RequestParam @Email String email) throws IOException {
        userService.sendEmailForPasswordRecovery(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(
            @RequestParam("token") String token,
            @RequestParam("pass") String newPassword
    ){
        return ResponseEntity.ok(userService.recoverPassword(token, newPassword));
    }
}
