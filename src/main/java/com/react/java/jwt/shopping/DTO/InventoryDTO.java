package com.react.java.jwt.shopping.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO 
{
    private Long inventoryId;

    @NotBlank(message = "Inventory name is required!")
    private String inventoryName;

    @NotNull(message = "Inventory quantity is required")
    @Min(value = 0, message = "Inventory quantity cannot be negative")
    private Integer inventoryQty;
}