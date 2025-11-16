package com.react.java.jwt.shopping.Interface;
import java.util.List;

import com.react.java.jwt.shopping.DTO.InventoryDTO;

public interface InventoryInterface 
{
    InventoryDTO createInventory (InventoryDTO inventoryDTO);
    InventoryDTO getInventory (Long inventoryId);
    List <InventoryDTO> getInventories ();
    InventoryDTO updateInventory (Long inventoryId, InventoryDTO inventoryDTO);
    void deleteInventory (Long inventoryId);
}