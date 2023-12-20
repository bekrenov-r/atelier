package com.group.atelier.business.review.reply;

import com.group.atelier.business.review.ReviewRepository;
import com.group.atelier.business.review.reply.dto.ReviewReplyResponse;
import com.group.atelier.business.review.reply.dto.ReviewReplyToResponseConverter;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Review;
import com.group.atelier.model.entity.ReviewReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class ReviewReplyService {
    private final ReviewRepository reviewRepository;
    private final ReviewReplyRepository replyRepository;
    private final ReviewReplyToResponseConverter converter;

    public ReviewReplyResponse createReviewReply(Long reviewId, String content) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ApplicationException(REVIEW_NOT_FOUND, reviewId));

        if(review.getReply() != null)
            throw new ApplicationException(REVIEW_ALREADY_HAS_REPLY, reviewId);

        ReviewReply reply = ReviewReply.builder()
                .review(review)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        review.setReply(reply);
        return converter.convert(replyRepository.save(reply));
    }

    public ReviewReplyResponse updateReviewReply(Long reviewId, String content) {
        ReviewReply reply = replyRepository.findById(reviewId)
                .orElseThrow(() -> new ApplicationException(REVIEW_REPLY_NOT_FOUND, reviewId));

        if(content != null){
            if(content.isBlank())
                throw new IllegalArgumentException("content must not be blank string");
            reply.setContent(content);
        }

        return converter.convert(replyRepository.save(reply));
    }

    public void deleteReviewReply(Long reviewId){
        ReviewReply reply = replyRepository.findById(reviewId)
                .orElseThrow(() -> new ApplicationException(REVIEW_REPLY_NOT_FOUND, reviewId));

        Review review = reviewRepository.findById(reviewId).get();
        review.setReply(null);
        reviewRepository.save(review);

        replyRepository.delete(reply);
    }
}
