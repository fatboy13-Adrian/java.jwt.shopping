package com.react.java.jwt.shopping.Entity;
import java.util.Date;
import com.react.java.jwt.shopping.Enum.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Order 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private double amount;
    private OrderStatus status;
    private Date orderDate;
}