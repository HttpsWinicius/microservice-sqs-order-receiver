package com.sqs.microservice.receiver.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class OrderRequestDTO {

    @JsonProperty("order")
    @Valid
    private List<ProductRequestDTO> productsRequest;
}
