package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.ProductDomain;
import com.sqs.microservice.receiver.service.mapper.ProductMapperInterface;
import com.sqs.microservice.receiver.util.TimeLoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends TimeLoggerUtil implements OrderServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductMapperInterface productMapper;

    @Autowired
    private SendOrderSqsInterface serviceSendOrderSqs;

    /**
     * Responsavel por mapear os pedidos recebidos
     * @param orderRequest Recebe os pedidos do controller
     * @return Retorna os pedidos do controller verificados e mapeados
     */
    @Override
    @Cacheable
    public UUID createOrder(OrderRequest orderRequest) {
        try {
            long startTime = startTimeLogger();
            LOGGER.info("Iniciando processamento de pedido...");



            List<ProductDomain> products = orderRequest.getProductsRequest()
                    .parallelStream()
                    .map(productMapper::toDomain)
                    .collect(Collectors.toList());

            OrderDomain orderDomain = new OrderDomain(products);

            serviceSendOrderSqs.sendToSqs(orderDomain);

            UUID orderId = orderDomain.getOrderId();

            LOGGER.info("Total total para processar pedido {} segundos", endTimeLogger(startTime));
            LOGGER.info("Processamento de pedido concluído...");

            return orderId;
        } catch (Exception e) {
            LOGGER.error("Erro ao processar pedido: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao processar pedido. Entre em contato com suporte", e);
        }
    }


}
