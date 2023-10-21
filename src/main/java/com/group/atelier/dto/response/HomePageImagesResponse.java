package com.group.atelier.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record HomePageImagesResponse(
        List<byte[]> jacketCoatImages,
        List<byte[]> midiCoatImages,
        List<byte[]> maxiCoatImages
) {
}
