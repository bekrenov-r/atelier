package com.group.atelier.service;

import com.group.atelier.business.PatternCalculator;
import com.group.atelier.dto.request.OrderRequest;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.*;
import com.group.atelier.repository.ClientRepository;
import com.group.atelier.repository.CoatModelRepository;
import com.group.atelier.repository.OrderRepository;
import com.group.atelier.util.LoggedUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.group.atelier.exception.ApplicationExceptionReason.COAT_MODEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PatternCalculatorService patternCalculatorService;
    private final LoggedUserUtil loggedUserUtil;
    private final ClientRepository clientRepository;
    private final CoatModelRepository coatModelRepository;

    public void createOrder(OrderRequest request) {
        Client client = clientRepository.findByUser(loggedUserUtil.getUser());
        PatternData patternData = patternCalculatorService.calculatePatternDataAndSave(request.productMetrics());
        CoatModel coatModel = coatModelRepository.findById(request.coatModelId())
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, request.coatModelId()));
        Order order = Order.builder()
                .coatModel(coatModel)
                .patternData(patternData)
                .client(client)
                .createdAt(LocalDateTime.now())
                .build();
        orderRepository.save(order);
    }
}
