package com.react.java.jwt.shopping.Exception;

public class ProductNameNotFoundException extends RuntimeException
{
    public ProductNameNotFoundException(String productName)
    {
        super ("Product name " +productName+ "not found!");
    }
}