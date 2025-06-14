package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.entity.ProductReview;
import com.ecommerce.E_commerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{clientId}/{productId}")
    public ResponseEntity<?> review(
            @PathVariable Long clientId,
            @PathVariable Long productId,
            @RequestParam int rating,
            @RequestParam String review
    ) {
        reviewService.addReview(clientId, productId, rating, review);
        return ResponseEntity.ok("Review added");
    }

    @GetMapping("/{productId}")
    public List<ProductReview> getReviews(@PathVariable Long productId) {
        return reviewService.getReviews(productId);
    }
}
