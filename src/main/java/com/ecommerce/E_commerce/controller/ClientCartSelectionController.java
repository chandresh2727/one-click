package com.ecommerce.E_commerce.controller;

import com.ecommerce.E_commerce.entity.Cart;
import com.ecommerce.E_commerce.entity.Selection;
import com.ecommerce.E_commerce.repository.CartRepository;
import com.ecommerce.E_commerce.repository.SelectionRepository;
import com.ecommerce.E_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientCartSelectionController {

    private final CartRepository cartRepository;
    private final SelectionRepository selectionRepository;

    @Autowired
    private final CartService cartService;

    public ClientCartSelectionController(CartRepository cartRepository, SelectionRepository selectionRepository, CartService cartService) {
        this.cartRepository = cartRepository;
        this.selectionRepository = selectionRepository;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public List<Cart> getClientCart(@RequestParam String clientName) {
        return cartRepository.findByClient_ClientName(clientName);
    }

    @GetMapping("/selection")
    public List<Selection> getClientSelection(@RequestParam String clientName) {
        return selectionRepository.findByClient_ClientName(clientName);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestParam String clientName,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.addToCart(clientName, productId, quantity);
        return ResponseEntity.ok(cart);
    }

}
