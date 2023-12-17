package com.group.atelier.review.reply;

import com.group.atelier.model.dto.response.ReviewReplyResponse;
import com.group.atelier.model.entity.ReviewReply;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReviewReplyToResponseConverter implements Converter<ReviewReply, ReviewReplyResponse> {
    @Override
    public ReviewReplyResponse convert(ReviewReply source) {
        return source != null
                ? new ReviewReplyResponse(source.getContent(), source.getCreatedAt())
                : null ;
    }
}
