package com.diploma.order_service.service;

import com.diploma.order_service.dto.OrderRequest;
import com.diploma.order_service.dto.OrderResponse;
import com.diploma.order_service.mapper.OrderLineItemsMapper;
import com.diploma.order_service.mapper.OrderMapper;
import com.diploma.order_service.model.Order;
import com.diploma.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderLineItemsMapper orderLineItemsMapper;

    public OrderResponse create(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream()
                        .map(orderLineItemsMapper::mapToObject)
                        .toList())
                .build();

        Order newOrder = orderRepository.save(order);

        return orderMapper.toResponse(newOrder);
    }
}
