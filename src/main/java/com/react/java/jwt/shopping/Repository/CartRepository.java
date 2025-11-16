package com.react.java.jwt.shopping.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react.java.jwt.shopping.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
    Optional <Cart> findByProductId (Long productId);
    boolean existsByProductId (Long productId);
}