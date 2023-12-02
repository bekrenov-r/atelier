package com.group.atelier.order;

import com.group.atelier.model.dto.request.OrderRequest;
import com.group.atelier.model.dto.response.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/unassigned")
    @Secured("EMPLOYEE")
    public ResponseEntity<List<OrderResponse>> getAllUnassignedOrders(){
        return ResponseEntity.ok(orderService.getAllUnassignedOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    @PatchMapping("/{id}/image")
    @Secured("EMPLOYEE")
    public ResponseEntity<Void> attachImageToOrder(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        orderService.attachImageToOrder(id, file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/assign/{orderId}")
    @Secured("EMPLOYEE")
    public ResponseEntity<Void> assignEmployeeToOrder(@PathVariable Long orderId){
        orderService.assignEmployeeToOrder(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/{id}/completed")
    @Secured("EMPLOYEE")
    public ResponseEntity<OrderResponse> markOrderAsCompleted(@PathVariable Long id){
        return ResponseEntity.ok(orderService.markOrderAsCompleted(id));
    }

    @PatchMapping("/{id}/cancel")
    @Secured("CLIENT")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
