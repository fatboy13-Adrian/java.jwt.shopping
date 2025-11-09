package com.react.java.jwt.shopping.Controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.java.jwt.shopping.DTO.PaymentDTO;
import com.react.java.jwt.shopping.Service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController             
@RequestMapping("/payments")
@RequiredArgsConstructor    
public class PaymerntController 
{
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity <PaymentDTO> createPayment (@RequestBody PaymentDTO paymentDTO)
    {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity <PaymentDTO> getPayment (@PathVariable Long paymentId)
    {
        PaymentDTO payment = paymentService.getPayment(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity <List <PaymentDTO>> getPayments ()
    {
        List <PaymentDTO> payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity <PaymentDTO> updatePayment (@PathVariable Long paymentId, @RequestBody PaymentDTO paymentDTO)
    {
        PaymentDTO paymentDto = paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.ok(paymentDto);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity <PaymentDTO> deletePayment (@PathVariable Long paymentId)
    {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build(); 
    }
}