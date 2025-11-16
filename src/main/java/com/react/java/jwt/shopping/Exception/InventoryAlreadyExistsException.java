package com.react.java.jwt.shopping.Exception;

public class InventoryAlreadyExistsException extends RuntimeException
{
    public InventoryAlreadyExistsException (Long inventoryId)
    {
        super ("Inventory with ID " + inventoryId + " already exists!");
    }
}
