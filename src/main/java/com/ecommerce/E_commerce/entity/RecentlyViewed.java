package com.ecommerce.E_commerce.entity;

import com.ecommerce.E_commerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recentlyViewed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentlyViewed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Client client;
    @ManyToOne private Product product;

    private LocalDateTime viewedAt;
}
