package com.react.java.jwt.shopping.Exception;

public class OrderAlreadyExistsException extends RuntimeException
{
    public OrderAlreadyExistsException (Long orderId)
    {
        super ("Order ID " + orderId + " exists.");
    }
}