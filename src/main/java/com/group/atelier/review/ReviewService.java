package com.group.atelier.review;

import com.group.atelier.business.coatmodel.CoatModelRepository;
import com.group.atelier.client.ClientRepository;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.dto.mapper.ReviewMapper;
import com.group.atelier.model.dto.request.ReviewRequest;
import com.group.atelier.model.dto.response.ReviewResponse;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.model.entity.Order;
import com.group.atelier.model.entity.Review;
import com.group.atelier.model.enums.OrderStatus;
import com.group.atelier.order.OrderRepository;
import com.group.atelier.security.Role;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final CurrentUserUtil currentUserUtil;
    private final CoatModelRepository coatModelRepository;
    private final ReviewMapper reviewMapper;
    private final OrderRepository orderRepository;

    @Value("${spring.custom.pagination.page-size}")
    private Integer pageSize;

    public Page<ReviewResponse> getReviewsForCoatModel(Long coatModelId, Integer page) {
        CoatModel coatModel = coatModelRepository.findById(coatModelId)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, coatModelId));
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Review> reviews = reviewRepository.findAllByCoatModel(coatModel, pageable);
        return reviews.map(reviewMapper::entityToResponse);
    }

    public void createReview(ReviewRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        CoatModel coatModel = coatModelRepository.findById(request.coatModelId())
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, request.coatModelId()));
        assertClientHasOrderForCoatModel(client, coatModel);
        Review review = Review.builder()
                .client(client)
                .coatModel(coatModel)
                .content(request.content())
                .rating(request.rating())
                .createdAt(LocalDateTime.now())
                .build();
        reviewRepository.save(review);
    }

    public ReviewResponse updateReview(Long id, String content, Short rating) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(REVIEW_NOT_FOUND, id));
        validateReviewOwnership(review);

        // todo: replace with custom validator
        if(content != null) {
            if(!content.isBlank())
                review.setContent(content);
            else
                throw new IllegalArgumentException("content must not be blank string");
        }
        if(rating != null)
            review.setRating(rating);

        return reviewMapper.entityToResponse(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(REVIEW_NOT_FOUND, id));

        if(currentUserUtil.getCurrentUser().hasRole(Role.CLIENT))
            validateReviewOwnership(review);

        reviewRepository.delete(review);
    }

    private void assertClientHasOrderForCoatModel(Client client, CoatModel coatModel) {
        List<Order> orders = orderRepository.findAllByClientAndCoatModel(client, coatModel);
        boolean hasAtLeastOneCompletedOrder = orders.stream()
                .anyMatch(o -> o.getStatus().equals(OrderStatus.COMPLETED));
        if(!hasAtLeastOneCompletedOrder)
            throw new ApplicationException(CANNOT_CREATE_REVIEW_WITHOUT_ORDER);
    }

    private void validateReviewOwnership(Review review) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(!review.getClient().equals(client))
            throw new ApplicationException(NOT_ENTITY_OWNER);
    }
}
