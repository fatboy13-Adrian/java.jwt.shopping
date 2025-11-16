package com.react.java.jwt.shopping.Exception;

public class InventoryNotFoundException extends RuntimeException
{
    public InventoryNotFoundException (Long inventoryId)
    {
        super ("Inventory ID " + inventoryId + " not found!");
    }
}