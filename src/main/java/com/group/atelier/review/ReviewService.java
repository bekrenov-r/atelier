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
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.group.atelier.exception.ApplicationExceptionReason.CANNOT_CREATE_REVIEW_WITHOUT_ORDER;
import static com.group.atelier.exception.ApplicationExceptionReason.COAT_MODEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final CurrentUserUtil currentUserUtil;
    private final CoatModelRepository coatModelRepository;
    private final ReviewMapper reviewMapper;
    private final OrderRepository orderRepository;

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
                .build();
        reviewRepository.save(review);
    }

    public ReviewResponse updateReview(Long id, ReviewRequest request) {
        return null;
    }
    public void deleteReview(Long id) {

    }

    private void assertClientHasOrderForCoatModel(Client client, CoatModel coatModel) {
        List<Order> orders = orderRepository.findAllByClientAndCoatModel(client, coatModel);
        boolean hasAtLeastOneCompletedOrder = orders.stream()
                .anyMatch(o -> o.getStatus().equals(OrderStatus.COMPLETED));
        if(!hasAtLeastOneCompletedOrder)
            throw new ApplicationException(CANNOT_CREATE_REVIEW_WITHOUT_ORDER);
    }
}
