package com.sqs.microservice.receiver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.SqsProperties;
import com.sqs.microservice.receiver.util.TimeLoggerUtil;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class SendOrderSqsServiceImpl extends TimeLoggerUtil implements SendOrderSqsInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private SqsProperties sqsProperties;

    @Autowired
    private SqsTemplate sqsTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Async
    public void sendToSqs(OrderDomain orderDomain) {
        try {
            long startTime = startTimeLogger();
            LOGGER.info("Iniciando envio para fila sqs...");

            Message<String> sendQueueMessage = this.transformMessageToJson(orderDomain);

            sqsTemplate.sendAsync(sqsProperties.getUrl(), sendQueueMessage);

            LOGGER.info("Tempo total para enviar pra fila sqs {} segundos", endTimeLogger(startTime));
            LOGGER.info("Envio para fila sqs finalizado...");
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar pedido para o SQS: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao enviar pedido para fila sqs. Entre em contato com suporte", e);
        }
    }



    private Message<String> transformMessageToJson(OrderDomain order) throws JsonProcessingException {
        String messageBody = objectMapper.writeValueAsString(order);

        Message<String> message = MessageBuilder.withPayload(messageBody).build();

        return message;
    }

}
