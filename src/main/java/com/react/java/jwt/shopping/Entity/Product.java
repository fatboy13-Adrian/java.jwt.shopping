package com.react.java.jwt.shopping.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "product")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName")
    private String productName;
    
    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productQty")
    private int productQty;

    @Column(name = "productPrice")
    private double productPrice;
}