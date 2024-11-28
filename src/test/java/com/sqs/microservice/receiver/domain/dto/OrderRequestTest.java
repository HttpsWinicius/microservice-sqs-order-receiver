package com.sqs.microservice.receiver.domain.dto;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.DTO.ProductRequest;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRequestTest {

    private static final String PRODUCT_NAME = "Cerveja";
    private static final String MSG_EXPECTED_LIST_PRODUCT_EMPTY = "A lista de produtos não pode estar vazia.";
    private ObjectMapper objectMapper;
    private Validator validator;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testOrderRequestDeserialization() throws Exception {
        String json = "{\"order\": [{\"nameProduct\": \"Cerveja\", \"priceProduct\": 2.0, \"amount\": 5}]}";

        OrderRequest orderRequest = objectMapper.readValue(json, OrderRequest.class);

        assertNotNull(orderRequest);
        assertNotNull(orderRequest.getProductsRequest());
        assertEquals(1, orderRequest.getProductsRequest().size());
        assertEquals(PRODUCT_NAME, orderRequest.getProductsRequest().get(0).getNameProduct());
    }

    @Test
    void testOrderRequestValidation_EmptyProductList() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductsRequest(List.of());

        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);

        assertEquals(1, violations.size());
        assertEquals(MSG_EXPECTED_LIST_PRODUCT_EMPTY, violations.iterator().next().getMessage());
    }

    @Test
    void testOrderRequestValidation_Valid() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductsRequest(List.of
                (new ProductRequest(PRODUCT_NAME, BigDecimal.ONE, 2),
                        new ProductRequest(PRODUCT_NAME, BigDecimal.TWO, 5)));

        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);

        assertTrue(violations.isEmpty());
    }
}
