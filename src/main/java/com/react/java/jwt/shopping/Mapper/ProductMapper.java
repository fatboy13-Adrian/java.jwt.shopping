package com.react.java.jwt.shopping.Mapper;
import org.mapstruct.Mapper;
import com.react.java.jwt.shopping.DTO.ProductDTO;
import com.react.java.jwt.shopping.Entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper 
{
    ProductDTO toDTO(Product product);
    Product toEntity (ProductDTO productDTO);
}