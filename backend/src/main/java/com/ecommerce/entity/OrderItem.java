package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem extends BaseEntity
{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(precision = 10, scale = 2)
	private BigDecimal unitPrice;

	@NotNull
	@DecimalMin(value = "0.01")
	@Column(precision = 10, scale = 2)
	private BigDecimal subtotal;

}