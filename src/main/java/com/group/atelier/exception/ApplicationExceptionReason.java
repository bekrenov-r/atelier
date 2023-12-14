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
    CANNOT_DELETE_COAT_MODEL("Coat model with id [%s] is referenced from [%s] orders and therefore cannot be deleted", HttpStatus.CONFLICT),
    ORDER_NOT_FOUND("Order with id [%s] does not exist", HttpStatus.NOT_FOUND),
    NOT_ENTITY_OWNER("You are not owner of this entity", HttpStatus.FORBIDDEN),
    CLIENT_METRICS_NOT_FOUND("Current client does not have recorded metrics", HttpStatus.NOT_FOUND),
    CLIENT_NOT_FOUND("Client with id [%s] does not exist", HttpStatus.NOT_FOUND),
    CLIENT_METRICS_ALREADY_EXIST("Current client already has recorded metrics", HttpStatus.CONFLICT),
    CLIENT_DETAILS_NOT_FOUND("There are no recorded details for current client", HttpStatus.NOT_FOUND),
    CLIENT_DETAILS_ALREADY_EXIST("Details for current client already exist", HttpStatus.CONFLICT),
    CLIENT_ID_REQUIRED("Client id is missing from request body though required", HttpStatus.BAD_REQUEST),
    ORDER_NOT_ASSIGNED("Order with id [%s] is not assigned to any employee yet", HttpStatus.CONFLICT),
    ORDER_NOT_IN_PROGRESS("Order with id [%s] is not in progress(either not assigned or cancelled)", HttpStatus.CONFLICT),
    ORDER_NOT_COMPLETED("Order with id [%s] is not completed", HttpStatus.CONFLICT),
    ORDER_ALREADY_ASSIGNED("Order with id [%s] is already assigned to some employee", HttpStatus.CONFLICT),
    ORDER_ALREADY_CANCELLED("Order with id [%s] is cancelled and cannot be updated", HttpStatus.CONFLICT),
    ORDER_ALREADY_COMPLETED("Order with id [%s] is already completed", HttpStatus.CONFLICT),
    EMPLOYEE_NOT_FOUND("Employee with id [%s] does not exist", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("Email address [%s] is already registered", HttpStatus.CONFLICT),
    EMPLOYEE_HAS_UNFINISHED_ORDERS("Employee with id [%s] has unfinished orders and therefore cannot be deleted", HttpStatus.CONFLICT),
    FILE_IS_NOT_IMAGE("Uploaded file is not image", HttpStatus.BAD_REQUEST),
    CANNOT_CREATE_REVIEW_WITHOUT_ORDER("Cannot create review: no completed orders found for current client", HttpStatus.BAD_REQUEST),
    REVIEW_NOT_FOUND("Review with id [%s] does not exist", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
}
