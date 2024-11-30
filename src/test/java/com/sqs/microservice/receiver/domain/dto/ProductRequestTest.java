package com.sqs.microservice.receiver.domain.dto;

import com.sqs.microservice.receiver.domain.DTO.ProductRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductRequestTest {

    private static final String PRODUCT_NAME_SUCCESS = "Cerveja Leve";
    private static final BigDecimal PRICE_PRODUCT_SUCCESS =  BigDecimal.valueOf(10.99);
    private static final int AMOUNT_PRODUCT_SUCCESS = 12;

    private static final String PRODUCT_NAME_EMPTY = "";
    private static final BigDecimal PRICE_PRODUCT_NEGATIVE =  BigDecimal.valueOf(-12.99);
    private static final int AMOUNT_PRODUCT_NOT_POSITIVE = 0;
    private static final String EXPECTED_ERROR_PRODUCT_NAME_EMPTY = "O nome do produto não pode estar vazio.";
    private static final String EXPECTED_ERROR_PRICE_NEGATIVE = "O preço do produto deve ser positivo.";
    private static final String EXPECTED_ERROR_AMOUNT_NOT_POSITIVE = "A quantidade do produto deve ser positiva.";


    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSuccessValidationValidProductRequest() {
        ProductRequest productRequest = new ProductRequest(PRODUCT_NAME_SUCCESS, PRICE_PRODUCT_SUCCESS, AMOUNT_PRODUCT_SUCCESS);

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testFailedValidationNameProductIsBlank() {
        ProductRequest productRequest = new ProductRequest(PRODUCT_NAME_EMPTY, PRICE_PRODUCT_SUCCESS, AMOUNT_PRODUCT_SUCCESS);

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        assertEquals(2, violations.size());
        assertEquals(EXPECTED_ERROR_PRODUCT_NAME_EMPTY, violations.iterator().next().getMessage());
    }

    @Test
    void testFailedValidationPriceProductIsNegative() {
        ProductRequest productRequest = new ProductRequest(PRODUCT_NAME_SUCCESS, PRICE_PRODUCT_NEGATIVE, AMOUNT_PRODUCT_SUCCESS);

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        assertEquals(1, violations.size());
        assertEquals(EXPECTED_ERROR_PRICE_NEGATIVE, violations.iterator().next().getMessage());
    }

    @Test
    void testFailedValidationAmountIsNotPositive() {
        ProductRequest productRequest = new ProductRequest(PRODUCT_NAME_SUCCESS, PRICE_PRODUCT_SUCCESS, AMOUNT_PRODUCT_NOT_POSITIVE);

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        assertEquals(1, violations.size());
        assertEquals(EXPECTED_ERROR_AMOUNT_NOT_POSITIVE, violations.iterator().next().getMessage());
    }

    @Test
    void testFailedValidationAllFieldsAreInvalid() {
        ProductRequest productRequest = new ProductRequest(PRODUCT_NAME_EMPTY, PRICE_PRODUCT_NEGATIVE, AMOUNT_PRODUCT_NOT_POSITIVE);

        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);

        assertEquals(4, violations.size());
    }


}
