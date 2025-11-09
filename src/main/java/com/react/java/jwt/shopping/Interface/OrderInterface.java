package com.react.java.jwt.shopping.Interface;
import java.util.List;
import com.react.java.jwt.shopping.DTO.OrderDTO;

public interface OrderInterface 
{
    OrderDTO createOrder (OrderDTO orderDTO);
    OrderDTO viewOrderById (Long orderId);
    List <OrderDTO> viewAllOrders ();
    OrderDTO updateOrder (Long orderId, OrderDTO orderDTO);
    void deleteOrder (Long orderId);
}