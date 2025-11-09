package com.react.java.jwt.shopping.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO 
{
    private Long productId;

    @NotBlank(message = "Product name is required!")
    private String productName;

    @NotBlank(message = "Product description is required!")
    private String productDescription;

    @NotNull(message = "Product quantity is required")
    @Min(value = 0, message = "Product quantity cannot be negative")
    private Integer productQty;

    @Min(value = 0, message = "Product price cannot be negative")
    private Double productPrice;
}