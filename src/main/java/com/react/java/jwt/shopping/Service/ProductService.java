package com.react.java.jwt.shopping.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.react.java.jwt.shopping.DTO.ProductDTO;
import com.react.java.jwt.shopping.Entity.Product;
import com.react.java.jwt.shopping.Exception.ProductNameExistsException;
import com.react.java.jwt.shopping.Exception.ProductNotFoundException;
import com.react.java.jwt.shopping.Interface.ProductInterface;
import com.react.java.jwt.shopping.Mapper.ProductMapper;
import com.react.java.jwt.shopping.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductInterface 
{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) 
    {
        if (productRepository.existsByProductName(productDTO.getProductName()))
            throw new ProductNameExistsException(productDTO.getProductName());

        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProduct(Long productId) 
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getProducts() 
    {
        return productRepository.findAll().stream().map(productMapper :: toDTO).toList();
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) 
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        if (productDTO.getProductName() != null && !productDTO.getProductName().isEmpty())
            product.setProductName(productDTO.getProductName());
        if (productDTO.getProductDescription() != null && !productDTO.getProductDescription().isEmpty())
            product.setProductDescription(productDTO.getProductDescription());
        if (productDTO.getProductQty() != null && productDTO.getProductQty() > 0)
            product.setProductQty(productDTO.getProductQty());
        
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public void deleteProduct(Long productId) 
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(product);
    }
}