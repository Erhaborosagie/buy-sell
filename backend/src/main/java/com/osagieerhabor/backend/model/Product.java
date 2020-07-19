package com.osagieerhabor.backend.model;

import javax.validation.constraints.NotNull;

public class Product {
    @NotNull(message = "Product name is required")
    private String name;

    private Double price;

    private String image;
}
