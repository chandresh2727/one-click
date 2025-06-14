package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.ProductReview;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.repository.ProductRepository;
import com.ecommerce.E_commerce.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ProductReviewRepository reviewRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private ClientRepo clientRepo;

    public void addReview(Long clientId, Long productId, int rating, String reviewText) {
        ProductReview review = new ProductReview();
        review.setClient(clientRepo.findById(clientId).orElseThrow());
        review.setProduct(productRepo.findById(productId).orElseThrow());
        review.setRating(rating);
        review.setReview(reviewText);
        review.setReviewedAt(LocalDateTime.now());
        reviewRepo.save(review);
    }

    public List<ProductReview> getReviews(Long productId) {
        return reviewRepo.findByProduct_Id(productId);
    }
}
