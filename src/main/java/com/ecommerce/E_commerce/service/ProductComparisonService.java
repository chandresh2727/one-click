package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductComparisonService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getComparisonDetails(List<Long> productIds) {
        return productRepo.findAllById(productIds);
    }
}
