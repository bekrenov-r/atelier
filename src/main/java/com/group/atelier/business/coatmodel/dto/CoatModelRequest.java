package com.group.atelier.business.coatmodel.dto;

import com.group.atelier.model.enums.CoatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CoatModelRequest(
        @NotBlank
        String name,
        @Positive
        Double price,
        @NotNull
        CoatType coatType,
        @NotBlank
        String videoUrl
) { }
