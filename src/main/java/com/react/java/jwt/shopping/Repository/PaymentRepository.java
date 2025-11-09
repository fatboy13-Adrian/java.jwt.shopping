package com.react.java.jwt.shopping.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react.java.jwt.shopping.Entity.Payment;
import com.react.java.jwt.shopping.Enum.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long>
{
    Optional <Payment> findByPaymentStatus(PaymentStatus paymentStatus);
    boolean existsByPaymentId (Long paymentId);
}