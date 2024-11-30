package com.sqs.microservice.receiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.env.MockEnvironment;

@Configuration
public class TestConfig {

    @Bean
    public MockEnvironment mockEnvironment() {
        return new MockEnvironment();
    }
}
