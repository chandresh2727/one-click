package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.Category;
import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.product.ProductDto;
import com.ecommerce.E_commerce.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDto dto) {
        validateProductDto(dto);

        Category category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryName()));

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        productRepository.save(product);
    }


    public void validateProductDto(ProductDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }
        if (dto.getPrice() < 1) {
            throw new IllegalArgumentException("Price must be at least 1");
        }
        if (dto.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (dto.getCategoryName() == null || dto.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category must not be blank");
        }
    }

    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        product.setActive(false);
        productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAllActive() {
        return productRepository.findByActiveTrue();
    }

    public List<Product> getByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));

        return productRepository.findByCategoryAndActiveTrue(category);
    }

    public void reduceStock(Long id, int qty) {
        Product product = getProduct(id);
        if (product.getStock() < qty) throw new RuntimeException("Insufficient stock");
        product.setStock(product.getStock() - qty);
        product.setUpdatedAt(java.time.LocalDateTime.now());
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDto(
                        product.getName(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getName(), // assuming category is an entity
                        product.getDescription(),
                        product.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProductsClient() {
        return productRepository.findAll();
    }

}
