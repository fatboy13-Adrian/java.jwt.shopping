package com.react.java.jwt.shopping.Mapper;
import org.mapstruct.Mapper;

import com.react.java.jwt.shopping.DTO.PaymentDTO;
import com.react.java.jwt.shopping.Entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper 
{
    PaymentDTO toDTO (Payment payment);
    Payment toEntity (PaymentDTO paymentDTO);
}