package com.group.atelier.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionReason {
    REGISTRATION_TOKEN_NOT_FOUND("Registration token [%s] does not exist", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User with username [%s] does not exist", HttpStatus.NOT_FOUND),
    COAT_MODEL_NOT_FOUND("Coat model with id [%s] does not exist", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND("Order with id [%s] does not exist", HttpStatus.NOT_FOUND),
    NOT_ENTITY_OWNER("You are not owner of this entity", HttpStatus.FORBIDDEN),
    CLIENT_METRICS_NOT_FOUND("Current client does not have recorded metrics", HttpStatus.NOT_FOUND),
    CLIENT_METRICS_ALREADY_EXIST("Current client already has recorded metrics", HttpStatus.CONFLICT),
    CLIENT_DETAILS_NOT_FOUND("There are no recorded details for current client", HttpStatus.NOT_FOUND),
    CLIENT_DETAILS_ALREADY_EXIST("Details for current client already exist", HttpStatus.CONFLICT),
    ORDER_NOT_ASSIGNED("Order with id [%s] is not assigned to any employee yet", HttpStatus.CONFLICT),
    ORDER_NOT_IN_PROGRESS("Order with id [%s] is not in progress(either not assigned or cancelled)", HttpStatus.CONFLICT),
    ORDER_ALREADY_ASSIGNED("Order with id [%s] is already assigned to some employee", HttpStatus.CONFLICT),
    ORDER_ALREADY_CANCELLED("Order with id [%s] is cancelled and cannot be updated", HttpStatus.CONFLICT),
    ORDER_ALREADY_COMPLETED("Order with id [%s] is already completed", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;
}
