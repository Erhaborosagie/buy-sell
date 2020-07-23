package com.osagieerhabor.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
public class Product extends BaseModel<Product> {
    @Size(min = 3)
    @NotNull(message = "Product name is required")
    private String name;

    @NotNull
    private String code;

    @NotNull
    private Double price=0.0;

    @NotNull
    private String image;

    private String description;

    @NotNull
    private int quantity=0;

    @NotNull
    private int views=0;

    @NotNull
    private int sold=0;

    @NotNull
    @ManyToOne
    private ProductType productType;

    @NotNull
    @ManyToOne
    private Category category;

    @ManyToOne
    @NotNull
    private Supplier suppliers;

    @PrePersist
    public void saving() {
        code = UUID.randomUUID().toString().substring(26).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product object = (Product) o;

        return getId().equals(object.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
