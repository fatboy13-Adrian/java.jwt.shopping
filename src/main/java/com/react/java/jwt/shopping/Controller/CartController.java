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

import com.react.java.jwt.shopping.DTO.CartDTO;
import com.react.java.jwt.shopping.Service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController 
{
    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity <CartDTO> createCart(@RequestBody CartDTO cartDTO)
    {
        return ResponseEntity.ok(cartService.createCart(cartDTO));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity <CartDTO> getCart(@PathVariable Long cartId)
    {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @GetMapping()
    public ResponseEntity <List <CartDTO>> getCarts()
    {
        return ResponseEntity.ok(cartService.getCarts());
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity <CartDTO> updateCart(@PathVariable Long cartId, @RequestBody CartDTO cartDTO)
    {
        return ResponseEntity.ok(cartService.updateCart(cartId, cartDTO));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity <CartDTO> deleteCart(@PathVariable Long cartId)
    {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}