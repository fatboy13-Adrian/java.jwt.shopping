package com.react.java.jwt.shopping.Entity;
import java.util.Date;

import com.react.java.jwt.shopping.Enum.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "order")
public class Order 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "orderAmount")
    private double orderAmount;

    @Column(name = "orderStatus")
    private OrderStatus orderStatus;

    @Column(name = "orderDate")
    private Date orderDate;
}