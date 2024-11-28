package com.sqs.microservice.receiver.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class OrderRequest {

    @JsonProperty("order")
    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    @Valid
    private List<ProductRequest> productsRequest;
}
