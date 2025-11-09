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
import com.react.java.jwt.shopping.DTO.ProductDTO;
import com.react.java.jwt.shopping.Service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController             
@RequestMapping("/products")
@RequiredArgsConstructor    
public class ProductController 
{
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
    {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> viewProductById(@PathVariable Long productId)
    {
        ProductDTO product = productService.viewProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> viewAllProducts()
    {
        List<ProductDTO> products = productService.viewAllProducts();
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO)
    {
        ProductDTO productDto = productService.updateProduct(productDTO, productId); 
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId)
    {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}