package com.react.java.jwt.shopping.Controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.react.java.jwt.shopping.DTO.InventoryDTO;
import com.react.java.jwt.shopping.Service.InventoryService;
import lombok.RequiredArgsConstructor;

@RestController             
@RequestMapping("/inventories")
@RequiredArgsConstructor    
public class InventoryController 
{
    private final InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity <InventoryDTO> createInventory (@RequestBody InventoryDTO inventoryDTO)
    {
        return ResponseEntity.ok (inventoryService.createInventory (inventoryDTO));
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity <InventoryDTO> getInventory (@PathVariable Long inventoryId)
    {
        return ResponseEntity.ok(inventoryService.getInventory (inventoryId));
    }

    @GetMapping
    public ResponseEntity <List <InventoryDTO>> getInventories ()
    {
        return ResponseEntity.ok(inventoryService.getInventories ());
    }

    @PatchMapping("/{inventoryId}")
    public ResponseEntity <InventoryDTO> updateInventory (@PathVariable Long inventoryId, @RequestBody InventoryDTO inventoryDTO)
    {
        return ResponseEntity.ok(inventoryService.updateInventory(inventoryId, inventoryDTO));
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity <InventoryDTO> deleteInventory (@PathVariable Long inventoryId)
    {
        inventoryService.deleteInventory (inventoryId);
        return ResponseEntity.noContent().build();
    }
}