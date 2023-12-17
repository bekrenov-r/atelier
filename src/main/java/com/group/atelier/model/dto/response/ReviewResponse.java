package com.group.atelier.model.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String clientFullName,
        String content,
        Short rating,
        LocalDateTime createdAt,
        ReviewReplyResponse reply
) { }
