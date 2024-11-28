package com.sqs.microservice.receiver.controller;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.service.OrderServiceInterface;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@Validated
public class OrderQueueController {

    @Autowired
    private OrderServiceInterface service;


    @PostMapping("/save")
    public ResponseEntity<?> saveOrderSqs(@RequestBody @Valid OrderRequest orderRequest) {

        UUID orderId = service.createOrder(orderRequest);

        return new ResponseEntity<>(Map.of("orderId", orderId), HttpStatus.CREATED);
    }



}
