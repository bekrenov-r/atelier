package com.group.atelier.business.review.reply.dto;

import java.time.LocalDateTime;

public record ReviewReplyResponse(
        String content,
        LocalDateTime createdAt
) { }
