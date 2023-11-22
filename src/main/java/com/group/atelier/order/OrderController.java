package com.group.atelier.order;

import com.group.atelier.model.dto.request.OrderRequest;
import com.group.atelier.model.dto.response.OrderResponse;
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
    @PatchMapping("/{id}/completed")
    public ResponseEntity<OrderResponse> markOrderAsCompleted(@PathVariable Long id){
        return ResponseEntity.ok(orderService.markOrderAsCompleted(id));
    }

    // todo: for role CLIENT
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
