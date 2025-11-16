package com.react.java.jwt.shopping.Mapper;
import org.mapstruct.Mapper;

import com.react.java.jwt.shopping.DTO.InventoryDTO;
import com.react.java.jwt.shopping.Entity.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper 
{
    InventoryDTO toDTO (Inventory inventory);
    Inventory toEntity (InventoryDTO inventoryDTO);
}