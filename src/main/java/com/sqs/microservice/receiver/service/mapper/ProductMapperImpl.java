package com.sqs.microservice.receiver.service.mapper;

import com.sqs.microservice.receiver.domain.DTO.ProductRequest;
import com.sqs.microservice.receiver.domain.ProductDomain;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapperInterface {

    public ProductDomain toDomain(ProductRequest productRequest) {

        return new ProductDomain(
                productRequest.getNameProduct(),
                productRequest.getPriceProduct(),
                productRequest.getAmount()
        );
    }

}
