package com.react.java.jwt.shopping.Mapper;
import org.mapstruct.Mapper;
import com.react.java.jwt.shopping.DTO.OrderDTO;
import com.react.java.jwt.shopping.Entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper 
{
    OrderDTO toDTO (Order order);
    Order toEntity (OrderDTO orderDTO);
}
