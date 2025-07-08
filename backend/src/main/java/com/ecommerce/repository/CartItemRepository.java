package com.ecommerce.repository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    List<CartItem> findByCart(Cart cart);
    
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    
    @Query("SELECT SUM(ci.quantity) FROM CartItem ci WHERE ci.cart = :cart")
    Integer getTotalItemsInCart(@Param("cart") Cart cart);
    
    @Query("SELECT SUM(ci.unitPrice * ci.quantity) FROM CartItem ci WHERE ci.cart = :cart")
    java.math.BigDecimal getTotalAmountInCart(@Param("cart") Cart cart);
    
    void deleteByCart(Cart cart);
}
