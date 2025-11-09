package com.react.java.jwt.shopping.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.react.java.jwt.shopping.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> 
{
    Optional <Product> findByProductName (String productName);
    boolean existsByProductName (String productName);
}