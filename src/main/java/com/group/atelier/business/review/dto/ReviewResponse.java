package com.group.atelier.business.review.dto;

import com.group.atelier.business.review.reply.dto.ReviewReplyResponse;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Long clientId,
        String clientFullName,
        String content,
        Short rating,
        LocalDateTime createdAt,
        ReviewReplyResponse reply
) { }
