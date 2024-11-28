package com.sqs.microservice.receiver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDomain {

    private String nameProduct;
    private BigDecimal priceProduct;
    private int amount;


}
