package com.react.java.jwt.shopping.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO 
{
    private Long cartId;
    private ProductDTO product;
    private int cartQty;
}