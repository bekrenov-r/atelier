package com.group.atelier.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionReason {
    REGISTRATION_TOKEN_NOT_FOUND("Registration token [%s] does not exist", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User with username [%s] does not exist", HttpStatus.NOT_FOUND),
    COAT_MODEL_NOT_FOUND("Coat model with id [%s] does not exist", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
}
