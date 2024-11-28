package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequest;
import com.sqs.microservice.receiver.domain.DTO.ProductRequest;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.ProductDomain;
import com.sqs.microservice.receiver.service.mapper.ProductMapperInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private ProductMapperInterface productMapper;

    @Mock
    private SendOrderSqsInterface sendOrderSqsService;

    @Mock
    private OrderRequest orderRequest;

    @Mock
    private ProductDomain productDomain;

    @Mock
    private OrderDomain orderDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder_Success() {
        ProductRequest product = new ProductRequest("Cerveja 1", BigDecimal.ZERO, 3);

        when(orderRequest.getProductsRequest()).thenReturn(Arrays.asList(product));
        when(productMapper.toDomain(product)).thenReturn(productDomain);

        when(orderDomain.getOrderId()).thenReturn(UUID.randomUUID());
        doNothing().when(sendOrderSqsService).sendToSqs(any(OrderDomain.class));

        UUID orderId = orderService.createOrder(orderRequest);

        verify(sendOrderSqsService, times(1)).sendToSqs(any(OrderDomain.class));

        assertNotNull(orderId);

        verify(productMapper, times(1)).toDomain(product);
    }
}
