package com.diploma.product_service.mapper;

import com.diploma.product_service.dto.ProductResponse;
import com.diploma.product_service.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);
}

