package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;

public interface OrderServiceInterface {

    OrderDomain createOrder(OrderRequest orderRequest);
}
