package com.react.java.jwt.shopping.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.react.java.jwt.shopping.DTO.PaymentDTO;
import com.react.java.jwt.shopping.Entity.Payment;
import com.react.java.jwt.shopping.Exception.PaymentAlreadyExistsException;
import com.react.java.jwt.shopping.Exception.PaymentNotFoundException;
import com.react.java.jwt.shopping.Interface.PaymentInterface;
import com.react.java.jwt.shopping.Mapper.PaymentMapper;
import com.react.java.jwt.shopping.Repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentInterface
{
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO)
    {
        if (paymentRepository.existsByPaymentId(paymentDTO.getPaymentId()))
            throw new PaymentAlreadyExistsException (paymentDTO.getPaymentId());

        Payment payment = paymentMapper.toEntity(paymentDTO);
        paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentDTO getPayment (Long paymentId)
    {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List <PaymentDTO> getPayments ()
    {
        return paymentRepository.findAll().stream().map(paymentMapper :: toDTO).toList();
    }

    @Override
    public PaymentDTO updatePayment (Long paymentId, PaymentDTO paymentDTO)
    {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));

        if (paymentDTO.getPaymentAmount() > 0)
            paymentDTO.setPaymentAmount(paymentDTO.getPaymentAmount());
        if (paymentDTO.getPaymentStatus() != null)
            paymentDTO.setPaymentStatus(paymentDTO.getPaymentStatus());
        if (paymentDTO.getPaymentDate() != null)
            paymentDTO.setPaymentDate(paymentDTO.getPaymentDate());

        paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public void deletePayment (Long paymentId)
    {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
        paymentRepository.delete(payment);
    }
}