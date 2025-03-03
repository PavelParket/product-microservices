package com.diploma.order_service.service;

import com.diploma.order_service.dto.InventoryResponse;
import com.diploma.order_service.dto.OrderRequest;
import com.diploma.order_service.dto.OrderResponse;
import com.diploma.order_service.mapper.OrderLineItemsMapper;
import com.diploma.order_service.mapper.OrderMapper;
import com.diploma.order_service.model.Order;
import com.diploma.order_service.model.OrderLineItems;
import com.diploma.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderLineItemsMapper orderLineItemsMapper;

    private final WebClient.Builder webClientBuilder;

    public OrderResponse create(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream()
                        .map(orderLineItemsMapper::mapToObject)
                        .toList())
                .build();

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] response = webClientBuilder.build().get()
                .uri("http://InventoryApplication/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result = Arrays.stream(Objects.requireNonNull(response))
                .allMatch(InventoryResponse::isInStock);

        if (result) {
            Order newOrder = orderRepository.save(order);
            return orderMapper.toResponse(newOrder);
        } else {
            throw new IllegalArgumentException("Error");
        }
    }
}
