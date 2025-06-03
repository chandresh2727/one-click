package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.product.ProductDto;
import com.ecommerce.E_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{category}")
    public ResponseEntity<?> addProduct(
            @PathVariable String category,
            @Valid @RequestBody ProductDto dto) {
        productService.addProduct(dto);
        return ResponseEntity.ok("Product added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted (soft)");
    }
}
