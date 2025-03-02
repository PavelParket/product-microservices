package com.diploma.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private Long id;

    private String skuCode;

    private Double price;

    private Integer amount;
}
