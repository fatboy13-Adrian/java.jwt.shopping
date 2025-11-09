package com.react.java.jwt.shopping.Exception;

public class ProductNameExistsException extends RuntimeException
{
    public ProductNameExistsException (String productName)
    {
        super ("Product name " + productName + "exists!");
    }
}