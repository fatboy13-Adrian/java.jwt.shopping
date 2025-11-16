package com.react.java.jwt.shopping.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.react.java.jwt.shopping.DTO.InventoryDTO;
import com.react.java.jwt.shopping.Entity.Inventory;
import com.react.java.jwt.shopping.Exception.InventoryAlreadyExistsException;
import com.react.java.jwt.shopping.Exception.InventoryNotFoundException;
import com.react.java.jwt.shopping.Interface.InventoryInterface;
import com.react.java.jwt.shopping.Mapper.InventoryMapper;
import com.react.java.jwt.shopping.Repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService implements InventoryInterface
{
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO)
    {
        if (inventoryRepository.existsByInventoryId(inventoryDTO.getInventoryId()))
            throw new InventoryAlreadyExistsException (inventoryDTO.getInventoryId());

        Inventory inventory = inventoryMapper.toEntity(inventoryDTO);
        inventoryRepository.save(inventory);
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventory(Long inventoryId)
    {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new InventoryNotFoundException(inventoryId));
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public List <InventoryDTO> getInventories()
    {
        return inventoryRepository.findAll().stream().map(inventoryMapper :: toDTO).toList();
    }

    @Override
    public InventoryDTO updateInventory(Long inventoryId, InventoryDTO inventoryDTO)
    {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        if (inventoryDTO.getInventoryName() != null && !inventoryDTO.getInventoryName().isEmpty())
            inventory.setInventoryName(inventoryDTO.getInventoryName());
        if (inventoryDTO.getInventoryQty() != null && inventoryDTO.getInventoryQty() > 0)
            inventory.setInventoryQty(inventoryDTO.getInventoryQty());

        inventoryRepository.save (inventory);
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public void deleteInventory(Long inventoryId)
    {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new InventoryNotFoundException(inventoryId));
        inventoryRepository.delete(inventory);
    }
}