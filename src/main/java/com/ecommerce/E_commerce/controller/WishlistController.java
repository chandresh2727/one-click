package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.service.WishlistService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{clientId}/{productId}")
    public ResponseEntity<?> add(@PathVariable Long clientId, @PathVariable Long productId) {
        wishlistService.addToWishlist(clientId, productId);
        return ResponseEntity.ok("Added to wishlist");
    }

    @DeleteMapping("/{clientId}/{productId}")
    public ResponseEntity<?> remove(@PathVariable Long clientId, @PathVariable Long productId) {
        wishlistService.removeFromWishlist(clientId, productId);
        return ResponseEntity.ok("Removed from wishlist");
    }

    @GetMapping("/{clientId}")
    public List<Product> getWishlist(@PathVariable Long clientId) {
        return wishlistService.getWishlist(clientId);
    }
}
