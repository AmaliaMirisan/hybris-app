package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity
{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotBlank
	@Column(name = "order_number", unique = true)
	private String orderNumber;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PENDING;

	@NotBlank
	@Size(max = 500)
	private String shippingAddress;

	@NotBlank
	@Size(max = 50)
	private String paymentMethod;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;

	public enum OrderStatus
	{
		PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
	}

}