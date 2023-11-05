package com.group.atelier.service;

import com.group.atelier.dto.mapper.OrderMapper;
import com.group.atelier.dto.request.OrderRequest;
import com.group.atelier.dto.response.OrderResponse;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.*;
import com.group.atelier.repository.ClientRepository;
import com.group.atelier.repository.CoatModelRepository;
import com.group.atelier.repository.OrderRepository;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PatternCalculatorService patternCalculatorService;
    private final CurrentUserUtil currentUserUtil;
    private final ClientRepository clientRepository;
    private final CoatModelRepository coatModelRepository;
    private final OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        PatternData patternData = patternCalculatorService.calculatePatternDataAndSave(request.productMetrics());
        CoatModel coatModel = coatModelRepository.findById(request.coatModelId())
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, request.coatModelId()));
        Order order = Order.builder()
                .coatModel(coatModel)
                .patternData(patternData)
                .client(client)
                .createdAt(LocalDateTime.now())
                .build();
        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getAllOrders() {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        List<Order> orders = orderRepository.findByClient(client);
        return orders.stream()
                .map(orderMapper::entityToResponse)
                .toList();
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        Client currentClient = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(!order.getClient().equals(currentClient))
            throw new ApplicationException(NOT_ENTITY_OWNER);
        return orderMapper.entityToResponse(order);
    }
}
