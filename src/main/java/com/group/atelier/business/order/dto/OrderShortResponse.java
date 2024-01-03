package com.group.atelier.business.order.dto;

import java.time.ZonedDateTime;

public record OrderShortResponse(
        Long id,
        String coatModelName,
        Double price,
        ZonedDateTime createdAt
) { }
