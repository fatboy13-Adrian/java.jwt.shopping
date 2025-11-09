package com.react.java.jwt.shopping.Exception;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException(Long productId)
    {
        super ("Product with ID " +productId+ "not found!");
    }
}