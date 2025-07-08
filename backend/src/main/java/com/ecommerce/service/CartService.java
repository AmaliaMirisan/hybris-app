package com.ecommerce.service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    public Cart getOrCreateCart(String sessionId, User user) {
        return cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setSessionId(sessionId);
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public Cart getCartBySessionId(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for session: " + sessionId));
    }

    public CartItem addItemToCart(String sessionId, Long productId, Integer quantity, User user) {
        Cart cart = getOrCreateCart(sessionId, user);
        Product product = productService.getProductById(productId);

        if (!product.isActive()) {
            throw new BadRequestException("Product is not available");
        }

        if (!productService.isStockAvailable(productId, quantity)) {
            throw new BadRequestException("Insufficient stock for product: " + product.getName());
        }

        CartItem existingItem = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            if (!productService.isStockAvailable(productId, newQuantity)) {
                throw new BadRequestException("Insufficient stock for requested quantity");
            }
            existingItem.setQuantity(newQuantity);
            return cartItemRepository.save(existingItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            return cartItemRepository.save(cartItem);
        }
    }

    public CartItem updateCartItemQuantity(String sessionId, Long itemId, Integer quantity) {
        Cart cart = getCartBySessionId(sessionId);
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to this cart");
        }

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
            return null;
        }

        if (!productService.isStockAvailable(cartItem.getProduct().getId(), quantity)) {
            throw new BadRequestException("Insufficient stock for requested quantity");
        }

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(String sessionId, Long itemId) {
        Cart cart = getCartBySessionId(sessionId);
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to this cart");
        }

        cartItemRepository.delete(cartItem);
    }

    public List<CartItem> getCartItems(String sessionId) {
        Cart cart = getCartBySessionId(sessionId);
        return cartItemRepository.findByCart(cart);
    }

    public void clearCart(String sessionId) {
        Cart cart = getCartBySessionId(sessionId);
        cartItemRepository.deleteByCart(cart);
    }

    public BigDecimal getCartTotal(String sessionId) {
        try {
            Cart cart = getCartBySessionId(sessionId);
            BigDecimal total = cartItemRepository.getTotalAmountInCart(cart);
            return total != null ? total : BigDecimal.ZERO;
        } catch (ResourceNotFoundException e) {
            return BigDecimal.ZERO;
        }
    }

    public Integer getCartItemCount(String sessionId) {
        try {
            Cart cart = getCartBySessionId(sessionId);
            Integer count = cartItemRepository.getTotalItemsInCart(cart);
            return count != null ? count : 0;
        } catch (ResourceNotFoundException e) {
            return 0;
        }
    }

    public boolean validateCart(String sessionId) {
        try {
            List<CartItem> cartItems = getCartItems(sessionId);
            
            for (CartItem item : cartItems) {
                Product product = item.getProduct();
                
                if (!product.isActive()) {
                    return false;
                }
                
                if (!productService.isStockAvailable(product.getId(), item.getQuantity())) {
                    return false;
                }
                
                if (!item.getUnitPrice().equals(product.getPrice())) {
                    item.setUnitPrice(product.getPrice());
                    cartItemRepository.save(item);
                }
            }
            return true;
        } catch (ResourceNotFoundException e) {
            return true; // Empty cart is valid
        }
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}
