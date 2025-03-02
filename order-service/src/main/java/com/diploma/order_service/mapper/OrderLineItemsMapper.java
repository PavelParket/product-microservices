package com.diploma.order_service.mapper;

import com.diploma.order_service.dto.OrderLineItemsDto;
import com.diploma.order_service.model.OrderLineItems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineItemsMapper {
    OrderLineItems mapToObject(OrderLineItemsDto orderLineItemsDto);
}
