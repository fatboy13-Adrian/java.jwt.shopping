package com.react.java.jwt.shopping.Interface;
import java.util.List;

import com.react.java.jwt.shopping.DTO.ProductDTO;

public interface ProductInterface 
{
    ProductDTO createProduct (ProductDTO productDTO);
    ProductDTO getProduct (Long productId);
    List<ProductDTO> getProducts ();
    ProductDTO updateProduct (ProductDTO productDTO, Long productId);
    void deleteProduct (Long productId);
}