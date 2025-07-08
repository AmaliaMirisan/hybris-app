package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

	@NotBlank
	@Column(name = "session_id", unique = true)
	private String sessionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartItem> cartItems;

	// Getters and setters
	public String getSessionId() { return sessionId; }
	public void setSessionId(String sessionId) { this.sessionId = sessionId; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public List<CartItem> getCartItems() { return cartItems; }
	public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}