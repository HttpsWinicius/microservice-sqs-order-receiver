package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.SqsProperties;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SendOrderSqsServiceImplTest {

    @InjectMocks
    private SendOrderSqsServiceImpl sendOrderSqsService;

    @Mock
    private SqsTemplate sqsTemplate;

    @Mock
    private SqsProperties sqsProperties;

    @Mock
    private OrderDomain orderDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendToSqs() {
        when(sqsProperties.getUrl()).thenReturn("https://mock-sqs-url");

        sendOrderSqsService.sendToSqs(orderDomain);

        verify(sqsTemplate, times(1)).send("https://mock-sqs-url", orderDomain);
    }

    @Test
    void testSendToSqs_ExceptionHandling() {

        when(sqsProperties.getUrl()).thenReturn("https://mock-sqs-url");
        doThrow(new RuntimeException("Falha ao enviar mensagem")).when(sqsTemplate).send(anyString(), any());

        try {
            sendOrderSqsService.sendToSqs(orderDomain);
        } catch (RuntimeException e) {
            assertEquals("Falha ao enviar pedido para fila sqs. Entre em contato com suporte", e.getMessage());
        }
    }
}

