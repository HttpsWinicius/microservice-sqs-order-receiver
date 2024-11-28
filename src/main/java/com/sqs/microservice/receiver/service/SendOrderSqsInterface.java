package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.OrderDomain;

public interface SendOrderSqsInterface {

    void sendToSqs(OrderDomain orderDomain);
}
