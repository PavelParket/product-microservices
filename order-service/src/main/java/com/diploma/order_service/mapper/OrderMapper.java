package com.diploma.order_service.mapper;

import com.diploma.order_service.dto.OrderResponse;
import com.diploma.order_service.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);
}
