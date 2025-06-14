package com.ecommerce.E_commerce.repository;

import com.ecommerce.E_commerce.entity.*;
import com.ecommerce.E_commerce.entity.Wishlist;
import com.ecommerce.E_commerce.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByClient_Id(Long cId);
    boolean existsByClient_IdAndProduct_Id(Long cId, Long productId);
    void deleteByClient_IdAndProduct_Id(Long cId, Long productId);
}

