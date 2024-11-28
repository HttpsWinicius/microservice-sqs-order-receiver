package com.sqs.microservice.receiver.service;

import com.sqs.microservice.receiver.domain.DTO.OrderRequestDTO;
import com.sqs.microservice.receiver.domain.OrderDomain;
import com.sqs.microservice.receiver.domain.ProductDomain;
import com.sqs.microservice.receiver.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceInterface {
    @Autowired
    private ProductMapper productMapper;


    /**
     * Responsavel por mapear os pedidos recebidos
     * @param orderRequestDTO Recebe os pedidos do controller
     * @return Retorna os pedidos do controller verificados e mapeados
     */
    @Override
    public OrderDomain createOrder(OrderRequestDTO orderRequestDTO)  {

        this.verifyOrder(orderRequestDTO);

        List<ProductDomain> products = orderRequestDTO.getProductsRequest()
                .stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());

        return new OrderDomain(products);
    }

    /**
     * Responsavel por verificar os pedidos recebidos
     * @param orderRequestDTO Recebe os pedidos para realizar a verificacao
     */
    private void verifyOrder (OrderRequestDTO orderRequestDTO) {

        if (orderRequestDTO.getProductsRequest() == null
                || orderRequestDTO.getProductsRequest().isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode estar vazia.");
        }

    }
}
