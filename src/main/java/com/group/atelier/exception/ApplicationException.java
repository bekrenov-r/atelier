package com.group.atelier.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {
    private ApplicationExceptionReason reason;
    private String message;
    private HttpStatus status;

    public ApplicationException(ApplicationExceptionReason reason) {
        this.message = reason.getMessage();
        this.status = reason.getStatus();
    }

    public ApplicationException(ApplicationExceptionReason reason, Object... args) {
        this.message = String.format(reason.getMessage(), args);
        this.reason = reason;
        this.status = reason.getStatus();
    }
}
