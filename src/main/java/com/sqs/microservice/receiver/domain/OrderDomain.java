package com.sqs.microservice.receiver.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDomain {

    @NotBlank(message = "O orderId não pode ser vazio.")
    @NotNull
    private UUID orderId;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    private List<ProductDomain> products;

    public OrderDomain(List<ProductDomain> products) {
        this.orderId = UUID.randomUUID();
        this.products = products;
    }

}
