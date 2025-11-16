package com.react.java.jwt.shopping;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.react.java.jwt.shopping.DTO.InventoryDTO;
import com.react.java.jwt.shopping.Entity.Inventory;
import com.react.java.jwt.shopping.Exception.InventoryAlreadyExistsException;
import com.react.java.jwt.shopping.Exception.InventoryNotFoundException;
import com.react.java.jwt.shopping.Mapper.InventoryMapper;
import com.react.java.jwt.shopping.Repository.InventoryRepository;
import com.react.java.jwt.shopping.Service.InventoryService;

@ExtendWith(MockitoExtension.class)
public class TestInventoryService 
{
    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryService inventoryService;

    private final Long inventoryId = 1L;

    @Test
    public void createInventorySuccessful() 
    {
        //Prepare DTO input
        InventoryDTO inventoryDTO = InventoryDTO.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Prepare entity returned by repository
        Inventory inventory = Inventory.builder().inventoryId(inventoryDTO.getInventoryId()).inventoryName(inventoryDTO.getInventoryName()).inventoryQty(inventoryDTO.getInventoryQty()).build();

        //Mock mapper behavior
        when(inventoryMapper.toEntity(inventoryDTO)).thenReturn(inventory);
        when(inventoryMapper.toDTO(inventory)).thenReturn(inventoryDTO);

        //Mock repository
        when(inventoryRepository.existsByInventoryId(inventoryDTO.getInventoryId())).thenReturn(false);
        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        //Call service
        InventoryDTO result = inventoryService.createInventory(inventoryDTO);

        //Assertions
        assertEquals(inventoryDTO.getInventoryId(), result.getInventoryId());
        assertEquals(inventoryDTO.getInventoryName(), result.getInventoryName());
        assertEquals(inventoryDTO.getInventoryQty(), result.getInventoryQty());

        //Verify repository.save() called once
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void createInventoryInventoryAlreadyExists() 
    {
        //Prepare DTO input
        InventoryDTO inventoryDTO = InventoryDTO.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Repository mock: inventory exists
        when(inventoryRepository.existsByInventoryId(inventoryDTO.getInventoryId())).thenReturn(true);

        //Assert that creating an existing inventory and throw the correct exception
        InventoryAlreadyExistsException ex = assertThrows(InventoryAlreadyExistsException.class, () -> inventoryService.createInventory(inventoryDTO));

        //Verify the exception message contains inventory ID
        assertTrue(ex.getMessage().contains(inventoryDTO.getInventoryId().toString()));

        //Verify save() is never called
        verify(inventoryRepository, times(0)).save(any(Inventory.class));
    }

    @Test
    public void getInventorySuccessful() 
    {
        //Prepare DTO input
        InventoryDTO inventoryDTO = InventoryDTO.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Prepare entity returned by repository
        Inventory inventory = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Mock repository to return entity
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        //Mock mapper
        when(inventoryMapper.toDTO(inventory)).thenReturn(inventoryDTO);

        //Call service
        InventoryDTO retrievedInventory = inventoryService.getInventory(1L);

        //Assertions
        assertEquals(inventoryDTO.getInventoryId(), retrievedInventory.getInventoryId());
        assertEquals(inventoryDTO.getInventoryName(), retrievedInventory.getInventoryName());
        assertEquals(inventoryDTO.getInventoryQty(), retrievedInventory.getInventoryQty());

        //Verify if repository was called once
        verify(inventoryRepository, times(1)).findById(1L);
    }

    @Test
    public void getInventoryInventoryNotFound() 
    {
        //Mock repository to return empty, simulating that inventory does not exists
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.empty());

        //Assert that service which throws inventory not found exception
        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class, () -> inventoryService.getInventory(inventoryId));

        //Verify the exception message contains inventory ID
        assertTrue(ex.getMessage().contains(inventoryId.toString()));

        //Verify repository was called once
        verify(inventoryRepository, times(1)).findById(1L);
    }

    @Test
    public void getInventoriesSuccessful() 
    {
        //Prepare entity returned by repository
        Inventory inventory1 = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();
        Inventory inventory2 = Inventory.builder().inventoryId(2L).inventoryName("productB").inventoryQty(20).build();
        List<Inventory> inventoryList = List.of(inventory1, inventory2);

        //Prepare corresponding DTOs
        InventoryDTO inventoryDTO1 = InventoryDTO.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();
        InventoryDTO inventoryDTO2 = InventoryDTO.builder().inventoryId(2L).inventoryName("productB").inventoryQty(20).build();

        //Mock repository and mapper
        when(inventoryRepository.findAll()).thenReturn(inventoryList);
        when(inventoryMapper.toDTO(inventory1)).thenReturn(inventoryDTO1);
        when(inventoryMapper.toDTO(inventory2)).thenReturn(inventoryDTO2);

        //Call service
        List<InventoryDTO> retrievedInventories = inventoryService.getInventories();

        //Assertions
        assertEquals(2, retrievedInventories.size());
        assertEquals(inventoryDTO1, retrievedInventories.get(0));
        assertEquals(inventoryDTO2, retrievedInventories.get(1));

        //Verify repository interaction
        verify(inventoryRepository, times(1)).findAll();
    }

    @Test
    public void getInventoriesIsEmpty() 
    {
        //Mock repository to return empty list
        when(inventoryRepository.findAll()).thenReturn(List.of());

        //Call service
        List<InventoryDTO> retrievedInventories = inventoryService.getInventories();

        //Assertions
        assertNotNull(retrievedInventories, "Returned list should not be null");
        assertTrue(retrievedInventories.isEmpty(), "Returned list should be empty");

        //Verify repository interaction
        verify(inventoryRepository, times(1)).findAll();
    }

    @Test
    public void updateInventorySuccessful() 
    {
        //Existing inventory in repository
        Inventory existingInventory = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Update DTO with new values
        InventoryDTO updateInventoryDTO = InventoryDTO.builder().inventoryName("productB").inventoryQty(10).build();

        // Mock repository behavior
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(existingInventory));
        when(inventoryMapper.toDTO(existingInventory)).thenReturn(updateInventoryDTO);

        //Call Service
        InventoryDTO updatedInventoryDTO = inventoryService.updateInventory(inventoryId, updateInventoryDTO);

        //Assertions
        assertEquals(updateInventoryDTO.getInventoryName(), updatedInventoryDTO.getInventoryName());
        assertEquals(updateInventoryDTO.getInventoryQty(), updatedInventoryDTO.getInventoryQty());

        //Verify repository interaction
        verify(inventoryRepository, times(1)).save(existingInventory);
        verify(inventoryRepository, times(1)).findById(inventoryId);
    }

    @Test
    public void updateInventoryNotFound() 
    {
        //Update DTO with new values
        InventoryDTO updateInventoryDTO = InventoryDTO.builder().inventoryName("productB").inventoryQty(20).build();

        //Mock repository to return empty
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.empty());

        //Assert exception is thrown
        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class, () -> inventoryService.updateInventory(inventoryId, updateInventoryDTO));
        assertTrue(ex.getMessage().contains(inventoryId.toString()));

        //Verify save is not called
        verify(inventoryRepository, times(0)).save(any(Inventory.class));
    }

    @Test
    public void partialUpdateInventorySuccessful() 
    {
        //Existing inventory in repository
        Inventory existingInventory = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(16).build();

        //DTO with valid inventory quantity update
        InventoryDTO updateInventoryDTO = InventoryDTO.builder().inventoryName(null).inventoryQty(15).build();

        //Mock repository behaviour
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(existingInventory));
        when(inventoryMapper.toDTO(existingInventory)).thenReturn(InventoryDTO.builder().inventoryId(inventoryId).inventoryName("productA").inventoryQty(15).build());

        //Call service
        InventoryDTO inventoryDTO = inventoryService.updateInventory(inventoryId, updateInventoryDTO);

        //Assertions: do not change the entity
        assertEquals("productA", inventoryDTO.getInventoryName());
        assertEquals(15, inventoryDTO.getInventoryQty());

        //Verify repository save is called once
        verify(inventoryRepository, times(1)).save(existingInventory);
    }

    @Test
    public void partialUpdateInventoryFailure() 
    {
        //Existing inventory in repository
        Inventory existingInventory = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //DTO with null or invalid fields
        InventoryDTO updateInventoryDTO = InventoryDTO.builder().inventoryName(null).inventoryQty(0).build();

        //Mock repository behaviour
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(existingInventory));
        when(inventoryMapper.toDTO(existingInventory)).thenReturn(InventoryDTO.builder().inventoryId(inventoryId).inventoryName("productA").inventoryQty(10).build());

        //Call service
        InventoryDTO inventoryDTO = inventoryService.updateInventory(inventoryId, updateInventoryDTO);

        //Nothing should have changed
        assertEquals("productA", inventoryDTO.getInventoryName());
        assertEquals(10, inventoryDTO.getInventoryQty());

        //Verify repository save is called once
        verify(inventoryRepository, times(1)).save(existingInventory);
    }

    @Test
    public void updateInentoryNotFound() 
    {
        //DTO with valid fields
        InventoryDTO updateInventoryDTO = InventoryDTO.builder().inventoryName("productB").inventoryQty(5).build();

        //Mock repository to return empty, simulating that inventory does not exists
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.empty());

        //Assert that service which throws inventory not found exception
        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class,() -> inventoryService.updateInventory(inventoryId, updateInventoryDTO));

        //Verify the exception message contains inventory ID
        assertTrue(ex.getMessage().contains(inventoryId.toString()));

        //Verify that repository is never called
        verify(inventoryRepository, never()).save(any(Inventory.class));
    }

    @Test
    public void deleteInventorySuccessful() 
    {
        //Existing inventory in repository
        Inventory existingInventory = Inventory.builder().inventoryId(1L).inventoryName("productA").inventoryQty(10).build();

        //Mock repository to return the inventory
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(existingInventory));

        //Call service
        inventoryService.deleteInventory(inventoryId);

        //Verify delete() to return the inventory
        verify(inventoryRepository, times(1)).delete(existingInventory);
    }

    @Test
    public void deleteInventoryNotFound() 
    {
        //Mock repository to return empty, simulating that inventory does not exists
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.empty());

        //Call serivce and assert exception
        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class, () -> inventoryService.deleteInventory(inventoryId));

        //Verify message contains inventory ID
        assertTrue(ex.getMessage().contains(inventoryId.toString()));

        //Verify delete() is never called
        verify(inventoryRepository, never()).delete(any(Inventory.class));
    }
}