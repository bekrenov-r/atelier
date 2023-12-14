package com.group.atelier.model.dto.response;

import com.group.atelier.model.enums.CoatType;
import lombok.Builder;

import java.util.List;

@Builder
public record CoatModelResponse(
        Long id,
        String name,
        Double price,
        byte[] image,
        String videoUrl,
        CoatType coatType,
        List<ReviewResponse> reviews
) { }
