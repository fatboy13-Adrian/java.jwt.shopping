package com.react.java.jwt.shopping.Exception;

public class CartNotFoundException extends RuntimeException
{
    public CartNotFoundException (Long cartId)
    {
        super ("Cart ID " + cartId + " not found!");
    }
}