package com.group.atelier.model.dto.response;

import com.group.atelier.model.CoatType;
import lombok.Builder;

@Builder
public record CoatModelResponse(
        Long id,
        String name,
        Double price,
        Integer creationTimeDays,
        byte[] image,
        String videoUrl,
        CoatType coatType
) { }
