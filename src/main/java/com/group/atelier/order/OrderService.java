package com.group.atelier.order;

import com.group.atelier.business.patterndata.PatternCalculator;
import com.group.atelier.model.dto.mapper.OrderMapper;
import com.group.atelier.model.dto.mapper.ProductMetricsMapper;
import com.group.atelier.model.dto.request.OrderRequest;
import com.group.atelier.model.dto.response.OrderResponse;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.*;
import com.group.atelier.business.patterndata.PatternCalculatorService;
import com.group.atelier.business.productmetrics.ProductMetricsService;
import com.group.atelier.client.ClientRepository;
import com.group.atelier.business.coatmodel.CoatModelRepository;
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
    private final PatternCalculator patternCalculator;
    private final CurrentUserUtil currentUserUtil;
    private final ClientRepository clientRepository;
    private final CoatModelRepository coatModelRepository;
    private final OrderMapper orderMapper;
    private final ProductMetricsService productMetricsService;
    private final ProductMetricsMapper productMetricsMapper;

    public OrderResponse createOrder(OrderRequest request) {
        ProductMetrics productMetrics = productMetricsService.save(request.productMetrics());
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        PatternData patternData = patternCalculatorService.calculatePatternDataAndSave(request.productMetrics());
        CoatModel coatModel = coatModelRepository.findById(request.coatModelId())
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, request.coatModelId()));
        Order order = Order.builder()
                .coatModel(coatModel)
                .productMetrics(productMetrics)
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
        return orderMapper.entityToResponse(order);
    }

    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        this.validateOrderOwnership(order);

        ProductMetrics updatedProductMetrics = productMetricsMapper.dtoToEntity(request.productMetrics());
        updatedProductMetrics.setId(order.getProductMetrics().getId());
        order.setProductMetrics(updatedProductMetrics);

        PatternData updatedPatternData = patternCalculator.doCalculate(request.productMetrics());
        updatedPatternData.setId(order.getPatternData().getId());
        order.setPatternData(updatedPatternData);

        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    private void validateOrderOwnership(Order order){
        Client currentClient = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(!order.getClient().equals(currentClient))
            throw new ApplicationException(NOT_ENTITY_OWNER);
    }
}
