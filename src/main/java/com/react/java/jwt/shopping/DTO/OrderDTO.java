package com.react.java.jwt.shopping.DTO;
import java.util.Date;

import com.react.java.jwt.shopping.Enum.OrderStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO 
{
    private Long orderId;

    @NotNull(message = "Order amount is required")
    @Min(value = 0, message = "Order amount cannot be negative")
    private double orderAmount;

    @NotNull(message = "Order status is required!")
    private OrderStatus orderStatus;

    @NotNull(message = "Order date is required!")
    private Date orderDate;
}