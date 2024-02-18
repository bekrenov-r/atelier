package com.group.atelier.business.order.dto;

import com.group.atelier.model.enums.CoatType;
import com.group.atelier.model.enums.OrderStatus;

import java.time.ZonedDateTime;

public record OrderShortResponse(
        Long id,
        String coatModelName,
        Double price,
        OrderStatus status,
        CoatType coatType,
        ZonedDateTime createdAt
) { }
