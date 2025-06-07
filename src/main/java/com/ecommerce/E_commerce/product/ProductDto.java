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

    public ProductDto(String name, double price, int stock, String categoryName, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
