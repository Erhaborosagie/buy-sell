package com.osagieerhabor.backend.dto;

import com.osagieerhabor.backend.model.Product;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ProductDto {
    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull(message = "Product price is required")
    private Double price;

    @NotNull(message = "Enter image url")
    @URL
    private String image;

    @NotNull(message = "Product description is required")
    private String description;

    @NotNull(message = "Product quantity is required")
    private int quantity;

    @NotNull
    private Long product_type_id;

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
