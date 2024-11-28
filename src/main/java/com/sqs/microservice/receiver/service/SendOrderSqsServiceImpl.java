package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.SqsProperties;
import com.sqs.microservice.receiver.util.TimeLoggerUtil;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendOrderSqsServiceImpl extends TimeLoggerUtil implements SendOrderSqsInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private SqsProperties sqsProperties;

    @Autowired
    private SqsTemplate sqsTemplate;


    @Async
    public void sendToSqs(OrderDomain orderDomain) {
        try {
            long startTime = startTimeLogger();
            LOGGER.info("Iniciando envio para fila sqs...");
            sqsTemplate.send(sqsProperties.getUrl(), orderDomain);

            LOGGER.info("Tempo total para enviar pra fila sqs {} segundos", endTimeLogger(startTime));
            LOGGER.info("Envio para fila sqs finalizado...");
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar pedido para o SQS: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao enviar pedido para fila sqs. Entre em contato com suporte", e);
        }
    }

}
