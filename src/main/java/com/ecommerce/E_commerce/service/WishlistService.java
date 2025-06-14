package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.Client;
import com.ecommerce.E_commerce.entity.Wishlist;
import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.repository.ProductRepository;
import com.ecommerce.E_commerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired private WishlistRepository wishlistRepo;
    @Autowired private ClientRepo clientRepo;
    @Autowired private ProductRepository productRepo;

    public void addToWishlist(Long clientId, Long productId) {
        if (!wishlistRepo.existsByClient_IdAndProduct_Id(clientId, productId)) {
            Wishlist w = new Wishlist();
            w.setClient(clientRepo.findById(clientId).orElseThrow());
            w.setProduct(productRepo.findById(productId).orElseThrow());
            w.setAddedAt(LocalDateTime.now());
            wishlistRepo.save(w);
        }
    }

    public void removeFromWishlist(Long clientId, Long productId) {
        wishlistRepo.deleteByClient_IdAndProduct_Id(clientId, productId);
    }

    public List<Product> getWishlist(Long clientId) {
        return wishlistRepo.findByClient_Id(clientId)
                .stream()
                .map(Wishlist::getProduct)
                .collect(Collectors.toList());
    }
}
