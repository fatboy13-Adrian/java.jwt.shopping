package com.react.java.jwt.shopping.Mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.react.java.jwt.shopping.DTO.CartDTO;
import com.react.java.jwt.shopping.Entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper 
{
    // @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "product", target = "product")
    CartDTO toDTO (Cart cart);

    default Cart toEntity (CartDTO cartDTO)
    {
        if (cartDTO == null)
            return null;

        Cart cart = new Cart();
        cart.setCartQty (cartDTO.getCartQty());
        return cart;
    }
}