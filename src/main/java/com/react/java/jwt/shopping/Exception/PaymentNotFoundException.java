package com.react.java.jwt.shopping.Exception;

public class PaymentNotFoundException extends RuntimeException
{
    public  PaymentNotFoundException (Long paymentId)
    {
        super ("Payment ID " + paymentId + " not found");
    }
}