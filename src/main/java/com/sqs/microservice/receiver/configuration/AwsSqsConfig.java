package com.sqs.microservice.receiver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class AwsSqsConfig {

    private static final String URI_LOCALSTACK = "http://localhost:4566";

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(URI_LOCALSTACK))
                .region(Region.US_EAST_1)
                .build();
    }

}
