package com.sandy.inventoryservice.service;

import com.sandy.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Transactional(readOnly=true)
    public boolean isInStock(List<String> skuCode)
    {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> );
    }
}
