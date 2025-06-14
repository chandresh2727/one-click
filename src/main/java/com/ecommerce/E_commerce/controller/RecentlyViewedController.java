package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.service.RecentlyViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recently-viewed")
public class RecentlyViewedController {

    @Autowired
    private RecentlyViewedService viewedService;

    @PostMapping("/{clientId}/{productId}")
    public ResponseEntity<?> markViewed(@PathVariable Long clientId, @PathVariable Long productId) {
        viewedService.addView(clientId, productId);
        return ResponseEntity.ok("Product marked as viewed");
    }

    @GetMapping("/{clientId}")
    public List<Product> getViewed(@PathVariable Long clientId) {
        return viewedService.getRecentlyViewed(clientId);
    }
}
