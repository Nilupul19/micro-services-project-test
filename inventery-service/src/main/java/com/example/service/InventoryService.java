package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.InventoryResponse;
import com.example.model.Inventory;
import com.example.repo.InventoryRepo;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;
    //find by skucode 

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
  
      return inventoryRepo.findBySkuCodeIn(skuCode)
                                 .stream()
                                 .map(this::mapToInventoryResonse)
                                 .collect(Collectors.toList());
    }
    
    private InventoryResponse mapToInventoryResonse(Inventory inventory){
      return InventoryResponse.builder()
                               .skuCode(inventory.getSkuCode())
                               .isInStock(inventory.getQuantity() > 0)
                               .build();
                                            
    }

}
