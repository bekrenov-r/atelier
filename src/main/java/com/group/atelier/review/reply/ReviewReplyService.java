package com.group.atelier.review.reply;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.dto.response.ReviewReplyResponse;
import com.group.atelier.model.entity.Review;
import com.group.atelier.model.entity.ReviewReply;
import com.group.atelier.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.group.atelier.exception.ApplicationExceptionReason.REVIEW_ALREADY_HAS_REPLY;
import static com.group.atelier.exception.ApplicationExceptionReason.REVIEW_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewReplyService {
    private final ReviewRepository reviewRepository;
    private final ReviewReplyRepository replyRepository;
    private final ReviewReplyToResponseConverter converter;

    public ReviewReplyResponse createReviewReply(Long id, String content) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(REVIEW_NOT_FOUND, id));

        if(review.getReply() != null)
            throw new ApplicationException(REVIEW_ALREADY_HAS_REPLY, id);

        ReviewReply reply = ReviewReply.builder()
                .review(review)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        review.setReply(reply);
        return converter.convert(replyRepository.save(reply));
    }
}
