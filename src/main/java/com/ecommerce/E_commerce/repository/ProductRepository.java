package com.ecommerce.E_commerce.repository;

import com.ecommerce.E_commerce.category.Category;
import com.ecommerce.E_commerce.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();
    List<Product> findByCategoryAndActiveTrue(Category category);
}
