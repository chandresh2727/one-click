package com.ecommerce.E_commerce.entity;

import com.ecommerce.E_commerce.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "productReview")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Product product;
    @ManyToOne private Client client;

    private int rating; // 1 to 5
    private String review;
    private LocalDateTime reviewedAt;

}
