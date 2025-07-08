package com.ecommerce.controller;

import com.ecommerce.dto.request.CartItemRequest;
import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/{sessionId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(@PathVariable String sessionId) {
        List<CartItem> cartItems = cartService.getCartItems(sessionId);
        CartResponse cartResponse = convertToCartResponse(sessionId, cartItems);
        return ResponseEntity.ok(ApiResponse.success(cartResponse));
    }

    @PostMapping("/{sessionId}/items")
    public ResponseEntity<ApiResponse<String>> addItemToCart(
            @PathVariable String sessionId,
            @Valid @RequestBody CartItemRequest request) {
        
        User user = getCurrentUser();
        cartService.addItemToCart(sessionId, request.getProductId(), request.getQuantity(), user);
        return ResponseEntity.ok(ApiResponse.success("Item added to cart successfully"));
    }

    @PutMapping("/{sessionId}/items/{itemId}")
    public ResponseEntity<ApiResponse<String>> updateCartItem(
            @PathVariable String sessionId,
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {
        
        cartService.updateCartItemQuantity(sessionId, itemId, quantity);
        return ResponseEntity.ok(ApiResponse.success("Cart item updated successfully"));
    }

    @DeleteMapping("/{sessionId}/items/{itemId}")
    public ResponseEntity<ApiResponse<String>> removeCartItem(
            @PathVariable String sessionId,
            @PathVariable Long itemId) {
        
        cartService.removeItemFromCart(sessionId, itemId);
        return ResponseEntity.ok(ApiResponse.success("Item removed from cart successfully"));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<ApiResponse<String>> clearCart(@PathVariable String sessionId) {
        cartService.clearCart(sessionId);
        return ResponseEntity.ok(ApiResponse.success("Cart cleared successfully"));
    }

    @PostMapping("/{sessionId}/validate")
    public ResponseEntity<ApiResponse<Boolean>> validateCart(@PathVariable String sessionId) {
        boolean isValid = cartService.validateCart(sessionId);
        return ResponseEntity.ok(ApiResponse.success("Cart validation completed", isValid));
    }

    @GetMapping("/session")
    public ResponseEntity<ApiResponse<String>> generateSession() {
        String sessionId = cartService.generateSessionId();
        return ResponseEntity.ok(ApiResponse.success("Session generated", sessionId));
    }

    private User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() 
                && !authentication.getName().equals("anonymousUser")) {
                return userService.getUserByUsername(authentication.getName());
            }
        } catch (Exception e) {
            // User not authenticated, return null for guest user
        }
        return null;
    }

    private CartResponse convertToCartResponse(String sessionId, List<CartItem> cartItems) {
        List<CartResponse.CartItemResponse> itemResponses = cartItems.stream()
            .map(this::convertToCartItemResponse)
            .collect(Collectors.toList());

        BigDecimal totalAmount = cartService.getCartTotal(sessionId);
        Integer totalItems = cartService.getCartItemCount(sessionId);

        return new CartResponse(sessionId, itemResponses, totalAmount, totalItems);
    }

    private CartResponse.CartItemResponse convertToCartItemResponse(CartItem cartItem) {
        CartResponse.CartItemResponse response = new CartResponse.CartItemResponse();
        response.setId(cartItem.getId());
        response.setProductId(cartItem.getProduct().getId());
        response.setProductName(cartItem.getProduct().getName());
        response.setUnitPrice(cartItem.getUnitPrice());
        response.setQuantity(cartItem.getQuantity());
        response.setSubtotal(cartItem.getSubtotal());
        response.setImageUrl(cartItem.getProduct().getImageUrl());
        return response;
    }
}
