package com.react.java.jwt.shopping.DTO;
import java.util.Date;

import com.react.java.jwt.shopping.Enum.PaymentStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO 
{
    private Long paymentId;

    @NotNull(message = "Payment amount is required")
    @Min(value = 0, message = "Payment amount cannot be negative")
    private double paymentAmount;

    @NotNull(message = "Pamynet status is required!")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Payment date is required!")
    private Date paymentDate;
}