package com.react.java.jwt.shopping.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.react.java.jwt.shopping.DTO.CartDTO;
import com.react.java.jwt.shopping.Entity.Cart;
import com.react.java.jwt.shopping.Entity.Product;
import com.react.java.jwt.shopping.Exception.CartNotFoundException;
import com.react.java.jwt.shopping.Exception.ProductNotFoundException;
import com.react.java.jwt.shopping.Interface.CartInterface;
import com.react.java.jwt.shopping.Mapper.CartMapper;
import com.react.java.jwt.shopping.Repository.CartRepository;
import com.react.java.jwt.shopping.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements CartInterface
{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDTO createCart (CartDTO cartDTO)
    {

        Product product = productRepository.findById(cartDTO.getProduct().getProductId()).orElseThrow(() -> new ProductNotFoundException(cartDTO.getProduct().getProductId()));

        Cart existingCart = cartRepository.findByProductId(product.getProductId()).orElse(null);

        if (existingCart != null)
        {
            existingCart.setCartQty(existingCart.getCartQty() + cartDTO.getCartQty());
            cartRepository.save(existingCart);
            return cartMapper.toDTO(existingCart);
        }

        Cart cart = new Cart();
        cart.setProduct(product);
        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    @Override
    public CartDTO getCart (Long cartId)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        return cartMapper.toDTO(cart);
    }

    @Override
    public List <CartDTO> getCarts ()
    {
        return cartRepository.findAll().stream().map(cartMapper:: toDTO).toList();
    }

    @Override
    public CartDTO updateCart (Long cartId, CartDTO cartDTO)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));

        if (cartDTO.getCartQty() > 0)
            cart.setCartQty(cart.getCartQty());
        
        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    @Override
    public void deleteCart (Long cartId)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        cartRepository.delete(cart);
    }
}