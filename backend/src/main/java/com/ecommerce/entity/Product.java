package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	@NotBlank
	@Size(min = 2, max = 200)
	private String name;

	@Size(max = 1000)
	private String description;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@Min(0)
	private Integer stockQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	private String imageUrl;

	@Column(name = "is_active")
	private boolean isActive = true;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartItem> cartItems;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;

	// Getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	public Integer getStockQuantity() { return stockQuantity; }
	public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }

	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

	public boolean isActive() { return isActive; }
	public void setActive(boolean active) { isActive = active; }

	public List<CartItem> getCartItems() { return cartItems; }
	public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }

	public List<OrderItem> getOrderItems() { return orderItems; }
	public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}