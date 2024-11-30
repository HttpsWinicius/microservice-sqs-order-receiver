package com.sqs.microservice.receiver.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BeanPropertyBindingResult;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalExceptionHandlerTest {

    private static final String EXPECTED_ERROR_PRODUCT_NAME = "O nome do produto não pode estar vazio.";
    private static final String EXPECTED_ERROR_PRICE = "O preço do produto deve ser positivo.";
    private static final String EXPECTED_ERROR_AMOUNT = "A quantidade do produto deve ser positiva.";
    private GlobalExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testSuccessValidationExceptions() {
        BeanPropertyBindingResult bindingResult = createBindingResultWithErrors();

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<?> response = exceptionHandler.handleValidationExceptions(exception);
        Map<String, String> errors = (Map<String, String>) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(errors);
        assertEquals(3, errors.size());
        assertEquals(EXPECTED_ERROR_PRODUCT_NAME, errors.get("nameProduct"));
        assertEquals(EXPECTED_ERROR_PRICE, errors.get("priceProduct"));
        assertEquals(EXPECTED_ERROR_AMOUNT, errors.get("amount"));
    }

    private BeanPropertyBindingResult createBindingResultWithErrors() {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "productRequest");
        bindingResult.addError(new FieldError("productRequest", "nameProduct", "O nome do produto não pode estar vazio."));
        bindingResult.addError(new FieldError("productRequest", "priceProduct", "O preço do produto deve ser positivo."));
        bindingResult.addError(new FieldError("productRequest", "amount", "A quantidade do produto deve ser positiva."));

        return bindingResult;
    }
}
