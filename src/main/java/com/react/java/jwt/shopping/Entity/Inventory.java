package com.react.java.jwt.shopping.Entity;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "inventory")
public class Inventory 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryId")
    private Long inventoryId;

    @Column(name = "inventoryName")
    private String inventoryName;

    @Column(name = "inventoryQty")
    private int inventoryQty;
}