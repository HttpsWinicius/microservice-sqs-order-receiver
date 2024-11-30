package com.sqs.microservice.receiver.domain;

import com.sqs.microservice.receiver.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.mock.env.MockEnvironment;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
class SqsPropertiesTest {

    private static final String URL_SQS = "https://sqs.us-east-1.amazonaws.com/274391779613/my-order-queue.fifo";
    private static final String URL_SQS_VALOR_VAZIO = "";


    @Autowired
    MockEnvironment environment;

    @Test
    void testDefaultPropertyBindingSuccess() {

        environment.setProperty("aws.sqs.localstack.url", URL_SQS);

        SqsProperties sqsProperties = Binder.get(environment)
                .bind("aws.sqs.localstack", SqsProperties.class)
                .orElseThrow(() -> new IllegalStateException("Propriedades de SQS não configuradas"));


        assertEquals(URL_SQS, sqsProperties.getUrl());
    }

    @Test
    void testMissingPropertyFailed() {

        MockEnvironment environment = new MockEnvironment();

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Binder.get(environment).bind("aws.sqs.localstack", SqsProperties.class).get();
        });

        assertTrue(exception.getMessage().contains("No value bound"));
    }



    @Test
    void testEmptyUrlProperty() {
        environment.setProperty("aws.sqs.localstack.url", URL_SQS_VALOR_VAZIO);

        SqsProperties sqsProperties = Binder.get(environment)
                .bind("aws.sqs.localstack", SqsProperties.class)
                .orElseThrow(() -> new IllegalStateException("Propriedades de SQS não configuradas"));



        assertEquals(URL_SQS_VALOR_VAZIO, sqsProperties.getUrl());
    }
}
