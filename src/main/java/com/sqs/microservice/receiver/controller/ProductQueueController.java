package com.sqs.microservice.receiver.controller;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.service.OrderServiceInterface;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@Validated
public class ProductQueueController {

    private static final String SQS_URL = "https://localhost.localstack.cloud:4566/000000000000/queue_order_receiver.fifo";

    @Autowired
    private OrderServiceInterface service;
    @Autowired
    private SqsTemplate sqsTemplate;


    @PostMapping("/save")
    public ResponseEntity<?> saveOrderSqs(@RequestBody @Valid OrderRequest orderRequest) {

        OrderDomain orderDomain = service.createOrder(orderRequest);
        sqsTemplate.send(SQS_URL, orderDomain);

        return new ResponseEntity<>(orderDomain, HttpStatus.CREATED);
    }



}
