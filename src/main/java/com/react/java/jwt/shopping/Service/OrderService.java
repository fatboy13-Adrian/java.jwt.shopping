package com.react.java.jwt.shopping.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.react.java.jwt.shopping.DTO.OrderDTO;
import com.react.java.jwt.shopping.Entity.Order;
import com.react.java.jwt.shopping.Exception.OrderAlreadyExistsException;
import com.react.java.jwt.shopping.Exception.OrderNotFoundException;
import com.react.java.jwt.shopping.Interface.OrderInterface;
import com.react.java.jwt.shopping.Mapper.OrderMapper;
import com.react.java.jwt.shopping.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderInterface
{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder (OrderDTO orderDTO)
    {
        if (orderRepository.existsByOrderId(orderDTO.getOrderId()))
            throw new OrderAlreadyExistsException(orderDTO.getOrderId());

        Order order = orderMapper.toEntity(orderDTO);
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO getOrder (Long orderId)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> getOrders() 
    {
        return orderRepository.findAll().stream().map(orderMapper :: toDTO).toList();
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if (orderDTO.getOrderAmount() > 0)
            order.setOrderAmount(orderDTO.getOrderAmount());
        if (orderDTO.getOrderStatus() != null)
            order.setOrderStatus(orderDTO.getOrderStatus());
        if (orderDTO.getOrderDate() != null)
            order.setOrderDate(orderDTO.getOrderDate());

        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public void deleteOrder(Long orderId)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.delete(order);
    }    
}