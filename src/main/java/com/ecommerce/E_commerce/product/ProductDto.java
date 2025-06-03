package com.ecommerce.E_commerce.product;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private double price;

    private int stock;

    private String categoryName;

    private String description;
    private String imageUrl;

}
