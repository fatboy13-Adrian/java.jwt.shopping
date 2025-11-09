package com.react.java.jwt.shopping.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.react.java.jwt.shopping.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long>
{
    boolean existsByOrderId(Long orderId);
}