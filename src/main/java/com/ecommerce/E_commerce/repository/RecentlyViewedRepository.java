package com.ecommerce.E_commerce.repository;

import com.ecommerce.E_commerce.entity.RecentlyViewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RecentlyViewedRepository extends JpaRepository<RecentlyViewed, Long> {
    List<RecentlyViewed> findTop10ByClient_IdOrderByViewedAtDesc(Long clientId);
}
