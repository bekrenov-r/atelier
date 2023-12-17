package com.group.atelier.model.dto.response;

import java.time.LocalDateTime;

public record ReviewReplyResponse(
        String content,
        LocalDateTime createdAt
) { }
