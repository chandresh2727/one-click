package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/products")
public class ClientProductController {

    @Autowired
    private final ProductService productService;

    public ClientProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/available")
    public List<Product> getAvailableProducts() {
        return productService.getAllActive().stream()
                .filter(Product::isAvailable)
                .toList();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getByCategory(@PathVariable String categoryName) {
        return productService.getByCategoryName(categoryName);
    }
}
