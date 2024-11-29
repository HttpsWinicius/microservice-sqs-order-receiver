package com.sqs.microservice.receiver.config;


import com.sqs.microservice.receiver.configuration.AwsSqsConfig;
import com.sqs.microservice.receiver.domain.SqsProperties;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AwsSqsConfigTest {

    private static final String REGION_SQS = "us-east-1";


    @Mock
    private SqsProperties sqsProperties;

    @Autowired
    private AwsSqsConfig awsSqsConfig;

    @Test
    void testSqsAsyncClientConfiguration() {

        SqsAsyncClient sqsAsyncClient = awsSqsConfig.sqsAsyncClient();

        assertNotNull(sqsAsyncClient);
        assertEquals(REGION_SQS, sqsAsyncClient.serviceClientConfiguration().region().id());
    }

}
