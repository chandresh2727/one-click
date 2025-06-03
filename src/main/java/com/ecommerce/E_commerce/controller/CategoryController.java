package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.category.Category;
import com.ecommerce.E_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("Category added");
    }
}

