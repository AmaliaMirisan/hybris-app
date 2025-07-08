package com.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderRequest {
    
    @NotBlank
    @Size(max = 500)
    private String shippingAddress;
    
    @NotBlank
    @Size(max = 50)
    private String paymentMethod;

    @NotBlank
    private String customerEmail;

    // Constructors
    public OrderRequest() {}

    public OrderRequest(String shippingAddress, String paymentMethod) {
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

}
