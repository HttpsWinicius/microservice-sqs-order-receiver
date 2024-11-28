package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.ProductDomain;
import com.sqs.microservice.receiver.service.mapper.ProductMapperInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductMapperInterface productMapper;


    /**
     * Responsavel por mapear os pedidos recebidos
     * @param orderRequest Recebe os pedidos do controller
     * @return Retorna os pedidos do controller verificados e mapeados
     */
    @Override
    public OrderDomain createOrder(OrderRequest orderRequest) {
        try {
            long startTime = startTimeLogger();
            LOGGER.info("Iniciando create order...");

            List<ProductDomain> products = orderRequest.getProductsRequest()
                    .stream()
                    .map(productMapper::toDomain)
                    .collect(Collectors.toList());


            LOGGER.info("Total time {} seconds", endTimeLogger(startTime));

            return new OrderDomain(products);
        } catch (Exception e) {
            LOGGER.error("Error while creating order: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create order", e);
        }
    }

    private long startTimeLogger() {
     return System.currentTimeMillis();
    }

    private long endTimeLogger(Long startTime) {
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        return totalTime;

    }

}
