package com.sqs.microservice.receiver.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDomainTest {

    private static final String PRODUCT_NAME = "Cerveja A";
    private static final BigDecimal PRICE_PRODUCT =  BigDecimal.valueOf(19.99);
    private static final int AMOUNT_PRODUCT = 10;

    @Test
    void testProductDomainDefaultConstructorSuccess() {
        ProductDomain product = new ProductDomain();

        assertNull(product.getNameProduct());
        assertNull(product.getPriceProduct());
        assertEquals(0, product.getAmount());
    }

    @Test
    void testProductDomainParameterizedConstructorSuccess() {
        ProductDomain product = new ProductDomain(PRODUCT_NAME, PRICE_PRODUCT, AMOUNT_PRODUCT);

        assertEquals(PRODUCT_NAME, product.getNameProduct());
        assertEquals(PRICE_PRODUCT, product.getPriceProduct());
        assertEquals(AMOUNT_PRODUCT, product.getAmount());
    }

    @Test
    void testSettersAndGettersSuccess() {
        ProductDomain product = new ProductDomain();
        product.setNameProduct(PRODUCT_NAME);
        product.setPriceProduct(PRICE_PRODUCT);
        product.setAmount(AMOUNT_PRODUCT);


        assertEquals(PRODUCT_NAME, product.getNameProduct());
        assertEquals(PRICE_PRODUCT, product.getPriceProduct());
        assertEquals(AMOUNT_PRODUCT, product.getAmount());
    }

    @Test
    void testEquality() {

        ProductDomain product1 = new ProductDomain(PRODUCT_NAME, PRICE_PRODUCT, AMOUNT_PRODUCT);
        ProductDomain product2 = new ProductDomain(PRODUCT_NAME, PRICE_PRODUCT, AMOUNT_PRODUCT);


        assertEquals(product1, product2);
    }

    @Test
    void testInequality() {
        ProductDomain product1 = new ProductDomain(PRODUCT_NAME, PRICE_PRODUCT, AMOUNT_PRODUCT);
        ProductDomain product2 = new ProductDomain("Cerveja A", BigDecimal.valueOf(19.99), 1);

        assertNotEquals(product1, product2);
    }

}
