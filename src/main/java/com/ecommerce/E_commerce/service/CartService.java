package com.ecommerce.E_commerce.service;

import com.ecommerce.E_commerce.entity.Cart;
import com.ecommerce.E_commerce.entity.Client;
import com.ecommerce.E_commerce.exception.ResourceNotFoundException;
import com.ecommerce.E_commerce.product.Product;
import com.ecommerce.E_commerce.repository.CartRepository;
import com.ecommerce.E_commerce.repository.ClientRepo;
import com.ecommerce.E_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ClientRepo clientRepo;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, ClientRepo clientRepo) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.clientRepo = clientRepo;
    }

    public Cart addToCart(String clientName, Long productId, int quantity) {
        Client client = clientRepo.findByClientName(clientName)
                .orElseThrow(() -> new ResourceNotFoundException("Client '" + clientName + "' does not exist."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found."));

        Cart cart = new Cart();
        cart.setClient(client);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public List<Cart> getCartByClient(String clientName) {
        return cartRepository.findByClient_ClientName(clientName);
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCartByClient(String clientName) {
        List<Cart> carts = getCartByClient(clientName);
        cartRepository.deleteAll(carts);
    }

}
