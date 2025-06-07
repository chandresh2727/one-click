package com.ecommerce.E_commerce.repository;

import com.ecommerce.E_commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByClient_ClientName(String clientName);
}
