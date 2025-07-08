package com.ecommerce.controller;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.UserService;

import jakarta.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest orderRequest,
            @RequestParam String sessionId) {

        User user = userService.getUserByUsername(orderRequest.getCustomerEmail());
        
        Order order = orderService.createOrderFromCart(
            sessionId, user, orderRequest.getShippingAddress(), orderRequest.getPaymentMethod());
        
        OrderResponse orderResponse = convertToOrderResponse(order);
        return ResponseEntity.ok(ApiResponse.success("Order created successfully", orderResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        OrderResponse orderResponse = convertToOrderResponse(order);
        return ResponseEntity.ok(ApiResponse.success(orderResponse));
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderByNumber(@PathVariable String orderNumber) {
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        OrderResponse orderResponse = convertToOrderResponse(order);
        return ResponseEntity.ok(ApiResponse.success(orderResponse));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Page<OrderResponse>>> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        User user = userService.getUserById(userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.getUserOrdersPaginated(user, pageable);
        Page<OrderResponse> orderResponses = orders.map(this::convertToOrderResponse);
        
        return ResponseEntity.ok(ApiResponse.success(orderResponses));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getCurrentUserOrders(String customerEmail) {
        User user = userService.getUserByUsername(customerEmail);
        
        List<Order> orders = orderService.getUserOrders(user);
        List<OrderResponse> orderResponses = orders.stream()
            .map(this::convertToOrderResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.success(orderResponses));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<ApiResponse<List<OrderResponse.OrderItemResponse>>> getOrderItems(@PathVariable Long id) {
        List<OrderItem> orderItems = orderService.getOrderItems(id);
        List<OrderResponse.OrderItemResponse> itemResponses = orderItems.stream()
            .map(this::convertToOrderItemResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.success(itemResponses));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OrderResponse>> cancelOrder(@PathVariable Long id) {
        Order order = orderService.cancelOrder(id);
        OrderResponse orderResponse = convertToOrderResponse(order);
        return ResponseEntity.ok(ApiResponse.success("Order cancelled successfully", orderResponse));
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus().name());
        response.setShippingAddress(order.getShippingAddress());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setCreatedAt(order.getCreatedAt());
        
        if (order.getOrderItems() != null) {
            List<OrderResponse.OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(this::convertToOrderItemResponse)
                .collect(Collectors.toList());
            response.setItems(itemResponses);
        }
        
        return response;
    }

    private OrderResponse.OrderItemResponse convertToOrderItemResponse(OrderItem orderItem) {
        OrderResponse.OrderItemResponse response = new OrderResponse.OrderItemResponse();
        response.setId(orderItem.getId());
        response.setProductId(orderItem.getProduct().getId());
        response.setProductName(orderItem.getProduct().getName());
        response.setUnitPrice(orderItem.getUnitPrice());
        response.setQuantity(orderItem.getQuantity());
        response.setSubtotal(orderItem.getSubtotal());
        return response;
    }
}
