package com.ecommerce.E_commerce.entity;

import com.ecommerce.E_commerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "selection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    private int quantity;

    private String clientEmail;

    private String clientPhone;

    private boolean confirmed;

    private LocalDate confirmedDate;

}
