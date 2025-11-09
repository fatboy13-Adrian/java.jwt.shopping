package com.react.java.jwt.shopping.Interface;
import java.util.List;

import com.react.java.jwt.shopping.DTO.PaymentDTO;

public interface PaymentInterface 
{
    PaymentDTO createPayment (PaymentDTO paymentDTO);
    PaymentDTO getPayment (Long paymentId);
    List <PaymentDTO> getPayments ();
    PaymentDTO updatePayment (Long paymentId, PaymentDTO paymentDTO);
    void deletePayment (Long paymentId);
}