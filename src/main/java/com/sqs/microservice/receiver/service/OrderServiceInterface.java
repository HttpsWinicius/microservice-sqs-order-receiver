package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequestDTO;
import com.sqs.microservice.receiver.domain.OrderDomain;

public interface OrderServiceInterface {

    OrderDomain createOrder(OrderRequestDTO orderRequestDTO);
}
