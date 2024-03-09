package com.group.atelier.business.coatmodel.dto;

import com.group.atelier.model.enums.CoatType;

public record CoatModelShortResponse(
        Long id,
        String name,
        CoatType coatType
) { }
