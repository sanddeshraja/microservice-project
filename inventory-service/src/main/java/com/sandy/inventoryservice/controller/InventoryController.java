package com.sandy.inventoryservice.controller;

import com.sandy.inventoryservice.model.InventoryResponse;
import com.sandy.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @GetMapping
//            ("/{sku-code}")

    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }


}
