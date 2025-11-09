package com.react.java.jwt.shopping.Exception;

public class OrderNotFoundException extends RuntimeException
{
    public OrderNotFoundException (Long orderId)
    {
        super ("Order ID " + orderId + " not found!");
    }
}