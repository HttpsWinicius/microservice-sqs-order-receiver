package com.sqs.microservice.receiver.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDomain {

    private UUID orderId;
    private List<ProductDomain> products;

    public OrderDomain(List<ProductDomain> products) {
        this.orderId = UUID.randomUUID();
        this.products = products;
    }

}
