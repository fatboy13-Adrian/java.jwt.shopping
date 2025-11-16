package com.react.java.jwt.shopping.Interface;

import java.util.List;

import com.react.java.jwt.shopping.DTO.CartDTO;

public interface CartInterface 
{
    CartDTO createCart(CartDTO cartDTO);
    CartDTO getCart (Long cartId);
    List <CartDTO> getCarts ();
    CartDTO updateCart (Long cartId, CartDTO cartDTO);
    void deleteCart (Long cartId);
}