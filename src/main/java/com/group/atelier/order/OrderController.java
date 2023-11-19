package com.group.atelier.order;

import com.group.atelier.model.dto.request.OrderRequest;
import com.group.atelier.model.dto.response.OrderResponse;
import com.group.atelier.model.enums.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrdersOfCurrentUser(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // todo: for role EMPLOYEE
    @GetMapping("/unassigned")
    public ResponseEntity<List<OrderResponse>> getAllUnassignedOrders(){
        return ResponseEntity.ok(orderService.getAllUnassignedOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    // todo: for role EMPLOYEE
    @PatchMapping("/assign/{orderId}")
    public ResponseEntity<Void> assignEmployeeToOrder(@PathVariable Long orderId){
        orderService.assignEmployeeToOrder(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    // todo: for role EMPLOYEE
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> changeOrderStatus(
            @PathVariable Long id,
            @RequestParam("value") OrderStatus status
    ){
        return ResponseEntity.ok(orderService.changeOrderStatus(id, status));
    }
}
