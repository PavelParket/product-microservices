package com.diploma.inventory_service.service;

import com.diploma.inventory_service.dto.InventoryResponse;
import com.diploma.inventory_service.mapper.InventoryMapper;
import com.diploma.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getAmount() > 0)
                                .build()
                )
                .toList();
    }
}
