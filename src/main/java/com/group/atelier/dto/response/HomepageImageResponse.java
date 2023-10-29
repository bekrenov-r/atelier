package com.group.atelier.dto.response;

import com.group.atelier.model.CoatType;
import lombok.Builder;

@Builder
public record HomepageImageResponse(
        byte[] image,
        String videoUrl,
        CoatType coatType
) { }
