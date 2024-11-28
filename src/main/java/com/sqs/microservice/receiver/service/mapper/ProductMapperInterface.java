package com.sqs.microservice.receiver.service.mapper;


import com.sqs.microservice.receiver.domain.DTO.ProductRequest;
import com.sqs.microservice.receiver.domain.ProductDomain;

public interface ProductMapperInterface {
    ProductDomain toDomain(ProductRequest productRequest);

}
