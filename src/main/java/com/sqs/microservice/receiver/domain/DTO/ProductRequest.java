package com.sqs.microservice.receiver.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
public class ProductRequest {

    @NotBlank(message = "O nome do produto não pode estar vazio.")
    @NotEmpty
    @JsonProperty("nameProduct")
    private String nameProduct;

    @Positive(message = "O preço do produto deve ser positivo.")
    @JsonProperty("priceProduct")
    private BigDecimal priceProduct;

    @Positive(message = "A quantidade do produto deve ser positiva.")
    @JsonProperty("amount")
    private int amount;


    public ProductRequest() {}
}
