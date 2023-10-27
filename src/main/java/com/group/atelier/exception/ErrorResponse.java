package com.group.atelier.exception;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
@Builder
public record ErrorResponse(
    LocalDateTime timestamp,
    HttpStatusCode status,
    String message
) {}
