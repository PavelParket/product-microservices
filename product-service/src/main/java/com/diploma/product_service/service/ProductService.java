package com.diploma.product_service.service;

import com.diploma.product_service.dto.ProductRequest;
import com.diploma.product_service.dto.ProductResponse;
import com.diploma.product_service.mapper.ProductMapper;
import com.diploma.product_service.model.Product;
import com.diploma.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product newProduct = productRepository.save(product);

        return productMapper.toResponse(newProduct);
    }

    public List<ProductResponse> getAll() {
        return productMapper.toResponseList(productRepository.findAll());
    }
}
