package com.group.atelier.business.order;

import com.group.atelier.business.coatmodel.CoatModelRepository;
import com.group.atelier.business.order.dto.OrderMapper;
import com.group.atelier.business.order.dto.OrderRequest;
import com.group.atelier.business.order.dto.OrderResponse;
import com.group.atelier.business.order.dto.OrderShortResponse;
import com.group.atelier.business.patterndata.PatternCalculator;
import com.group.atelier.business.patterndata.PatternCalculatorService;
import com.group.atelier.business.productmetrics.ProductMetricsService;
import com.group.atelier.business.productmetrics.dto.ProductMetricsDTO;
import com.group.atelier.business.productmetrics.dto.ProductMetricsMapper;
import com.group.atelier.client.ClientRepository;
import com.group.atelier.employee.EmployeeRepository;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.*;
import com.group.atelier.model.enums.OrderStatus;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

import static com.group.atelier.exception.ApplicationExceptionReason.*;
import static com.group.atelier.security.Role.CLIENT;
import static com.group.atelier.security.Role.EMPLOYEE;

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
    private final EmployeeRepository employeeRepository;
    private final OrderValidator orderValidator;

    public OrderResponse createOrder(OrderRequest request) {
        ProductMetrics productMetrics = productMetricsService.save(request.productMetrics());
        PatternData patternData = patternCalculatorService.calculatePatternDataAndSave(request.productMetrics());
        CoatModel coatModel = coatModelRepository.findById(request.coatModelId())
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, request.coatModelId()));
        Order order = Order.builder()
                .coatModel(coatModel)
                .productMetrics(productMetrics)
                .createdAt(ZonedDateTime.now())
                .patternData(patternData)
                .build();

        return currentUserUtil.getCurrentUser().hasRole(CLIENT)
                ? createOrderAsClient(order)
                : createOrderAsEmployee(order, request.clientId());
    }

    private OrderResponse createOrderAsClient(Order order){
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        order.setClient(client);
        order.setStatus(OrderStatus.PENDING);
        System.out.println(order.getCreatedAt());
        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    private OrderResponse createOrderAsEmployee(Order order, Long clientId){
        if(clientId == null)
            throw new ApplicationException(CLIENT_ID_REQUIRED);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ApplicationException(CLIENT_NOT_FOUND, clientId));
        order.setClient(client);

        Order savedOrder = orderRepository.save(order);
        assignEmployeeToOrder(savedOrder.getId());
        return orderMapper.entityToResponse(savedOrder);
    }

    public List<OrderShortResponse> getAllOrders() {
        List<Order> orders = this.getAllOrdersDependingOnRole();
        return orders.stream()
                .map(orderMapper::entityToShortResponse)
                .toList();
    }

    private List<Order> getAllOrdersDependingOnRole(){
        User currentUser = currentUserUtil.getCurrentUser();
        if(currentUser.getRoles().contains(EMPLOYEE)){
            Employee employee = employeeRepository.findByUser(currentUser);
            return orderRepository.findAllByEmployee(employee);
        } else {
            Client client = clientRepository.findByUser(currentUser);
            return orderRepository.findAllByClient(client);
        }
    }


    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        return orderMapper.entityToResponse(order);
    }

    public List<OrderShortResponse> getAllUnassignedOrders() {
        List<Order> orders = orderRepository.findAllUnassignedOrders();
        return orders.stream()
                .map(orderMapper::entityToShortResponse)
                .toList();
    }

    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        orderValidator.validateOrderOwnershipByClient(order);
        orderValidator.assertOrderIsNotCancelled(order);

        ProductMetrics updatedProductMetrics = productMetricsMapper.dtoToEntity(request.productMetrics());
        updatedProductMetrics.setId(order.getProductMetrics().getId());
        order.setProductMetrics(updatedProductMetrics);

        PatternData updatedPatternData = patternCalculator.doCalculate(request.productMetrics());
        updatedPatternData.setId(order.getPatternData().getId());
        order.setPatternData(updatedPatternData);

        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    public void assignEmployeeToOrder(Long orderId) {
        Employee currentEmployee = employeeRepository.findByUser(currentUserUtil.getCurrentUser());
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, orderId));
        orderValidator.validateBeforeAssignment(order);
        order.setEmployee(currentEmployee);
        order.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(order);
    }

    public OrderResponse markOrderAsCompleted(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));

        orderValidator.assertOrderIsNotCancelled(order);
        orderValidator.assertOrderIsInProgress(order);
        orderValidator.validateOrderOwnershipByEmployee(order);
        order.setStatus(OrderStatus.COMPLETED);
        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    public OrderResponse cancelOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));

        orderValidator.validateOrderOwnershipByClient(order);
        orderValidator.assertOrderIsNotCompleted(order);
        order.setStatus(OrderStatus.CANCELLED);
        return orderMapper.entityToResponse(orderRepository.save(order));
    }

    public ProductMetricsDTO getProductMetricsOfOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        return productMetricsMapper.entityToDto(order.getProductMetrics());
    }
}
