package com.osagieerhabor.backend.dto;

import com.osagieerhabor.backend.model.Product;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {
    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull(message = "Product price is required")
    private Double price=0.0;

    @NotNull(message = "Enter image url")
    @URL
    private String image;

    @NotNull(message = "Product price is required")
    private String description;

    @NotNull(message = "Product price is required")
    private int quantity=0;

    @NotNull
    private Long productTypeId;

    public Product toProduct(){
        Product product = new Product();
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setName(name);
        return product;
    }
}
