package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.RecentlyViewed;
import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.repository.ProductRepository;
import com.ecommerce.E_commerce.repository.RecentlyViewedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecentlyViewedService {

    @Autowired
    private RecentlyViewedRepository viewedRepo;
    @Autowired private ClientRepo clientRepo;
    @Autowired private ProductRepository productRepo;

    public void addView(Long clientId, Long productId) {
        RecentlyViewed view = new RecentlyViewed();
        view.setClient(clientRepo.findById(clientId).orElseThrow());
        view.setProduct(productRepo.findById(productId).orElseThrow());
        view.setViewedAt(LocalDateTime.now());
        viewedRepo.save(view);
    }

    public List<Product> getRecentlyViewed(Long clientId) {
        return viewedRepo.findTop10ByClient_IdOrderByViewedAtDesc(clientId)
                .stream()
                .map(RecentlyViewed::getProduct)
                .collect(Collectors.toList());
    }
}
