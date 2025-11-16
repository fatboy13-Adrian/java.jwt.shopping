package com.react.java.jwt.shopping.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react.java.jwt.shopping.Entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long>
{
    boolean existsByInventoryId (Long inventoryId);
}