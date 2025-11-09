package com.react.java.jwt.shopping.Exception;

public class PaymentAlreadyExistsException extends RuntimeException
{
    public PaymentAlreadyExistsException (Long paymentId)
    {
        super ("Payment ID " + paymentId + " already exists!");
    }
}
