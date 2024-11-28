package com.sqs.microservice.receiver.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto não pode estar vazio.")
    private String nameProduct;

    @Positive(message = "O preço do produto deve ser positivo.")
    private BigDecimal priceProduct;

    @Positive(message = "A quantidade do produto deve ser positiva.")
    private int amount;
}
