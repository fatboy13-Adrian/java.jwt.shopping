package com.react.java.jwt.shopping.Interface;
import java.util.List;

import com.react.java.jwt.shopping.DTO.PaymentDTO;

public interface PaymentInterface 
{
    PaymentDTO createPayment (PaymentDTO paymentDTO);
    PaymentDTO viewPaymentById (Long paymentId);
    List <PaymentDTO> viewAllPayments ();
    PaymentDTO updatePayment (Long paymentId, PaymentDTO paymentDTO);
    void deletePayment (Long paymentId);
}