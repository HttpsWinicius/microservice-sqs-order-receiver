package com.sqs.microservice.receiver.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDomainTest {

    @Test
    void testOrderDomainCreation() {
        ProductDomain product = new ProductDomain("Cerveja 1", BigDecimal.ZERO, 3);
        OrderDomain order = new OrderDomain(List.of(product));

        assertNotNull(order.getOrderId(), "Order nao pode ser nulo");
        assertEquals(1, order.getProducts().size(), "Lista de produto deve conter pelo menos 1 produto");
    }

}

