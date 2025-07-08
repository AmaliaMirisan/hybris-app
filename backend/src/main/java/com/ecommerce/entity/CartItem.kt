package com.ecommerce.entity

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

@Entity
@Table(name = "cart_items")
class CartItem : BaseEntity() {
    // Getters and setters
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    var cart: Cart? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product? = null

    var quantity: @NotNull @Min(1) Int? = null

    @Column(precision = 10, scale = 2)
    var unitPrice: @NotNull @DecimalMin(value = "0.01") BigDecimal? = null

    val subtotal: BigDecimal
        get() = unitPrice!!.multiply(BigDecimal.valueOf(quantity!!.toLong()))
}