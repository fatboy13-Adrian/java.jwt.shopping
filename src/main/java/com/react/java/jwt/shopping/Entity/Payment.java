package com.react.java.jwt.shopping.Entity;
import java.util.Date;

import com.react.java.jwt.shopping.Enum.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;

    @Column(name = "paymentAmount")
    private double paymentAmount;

    @Column(name = "paymentStatus")
    private PaymentStatus paymentStatus;

    @Column(name = "paymentDate")
    private Date paymentDate;
}