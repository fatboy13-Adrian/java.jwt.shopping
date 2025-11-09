package com.react.java.jwt.shopping.Controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.react.java.jwt.shopping.DTO.OrderDTO;
import com.react.java.jwt.shopping.Service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController             
@RequestMapping("/orders")
@RequiredArgsConstructor    
public class OrderController 
{
    private final OrderService orderService;

     @PostMapping("/create")
     public ResponseEntity <OrderDTO> createOrder(@RequestBody OrderDTO orderDTO)
     {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
     }

     @GetMapping("/{orderId}")
     public ResponseEntity <OrderDTO> viewOrderById (@PathVariable Long orderId)
     {
        OrderDTO order = orderService.viewOrderById(orderId);
        return ResponseEntity.ok(order);
     }

     @GetMapping
     public ResponseEntity <List <OrderDTO>> viewAllOrders ()
     {
        List <OrderDTO> orders = orderService.viewAllOrders();
        return ResponseEntity.ok(orders);
     }

     @PatchMapping("/{orderId}")
     public ResponseEntity <OrderDTO> updateOrder (@PathVariable Long orderId, OrderDTO orderDTO)
     {
        OrderDTO orderDto = orderService.updateOrder(orderId, orderDTO); 
        return ResponseEntity.ok(orderDto);
     }

     @DeleteMapping("/{orderId}")
     public ResponseEntity <OrderDTO> deleteOrder(@PathVariable Long orderId)
     {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build(); 
     }
}