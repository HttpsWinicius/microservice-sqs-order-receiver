package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;

import java.util.UUID;

public interface OrderServiceInterface {

    UUID createOrder(OrderRequest orderRequest);
}
