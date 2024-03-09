package com.group.atelier.business.order.dto;

import com.group.atelier.business.coatmodel.dto.CoatModelShortResponse;
import com.group.atelier.model.enums.OrderStatus;

import java.time.ZonedDateTime;

public record OrderShortResponse(
        Long id,
        Double price,
        OrderStatus status,
        ZonedDateTime createdAt,
        CoatModelShortResponse coatModel
) { }
