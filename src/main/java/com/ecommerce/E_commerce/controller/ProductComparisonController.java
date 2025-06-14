package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.service.ProductComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compare")
public class ProductComparisonController {

    @Autowired
    private ProductComparisonService compareService;

    @PostMapping
    public List<Product> compareProducts(@RequestBody List<Long> productIds) {
        return compareService.getComparisonDetails(productIds);
    }
}
