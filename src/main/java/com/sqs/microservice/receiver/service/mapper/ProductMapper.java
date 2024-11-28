package com.sqs.microservice.receiver.service.mapper;

import com.sqs.microservice.receiver.domain.DTO.ProductRequestDTO;
import com.sqs.microservice.receiver.domain.ProductDomain;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDomain toDomain(ProductRequestDTO productRequestDTO) {
        return new ProductDomain(
                productRequestDTO.getNameProduct(),
                productRequestDTO.getPriceProduct(),
                productRequestDTO.getAmount()
        );
    }

}
